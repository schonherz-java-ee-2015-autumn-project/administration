package hu.schonherz.administration.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.administration.persistence.dao.CargoDao;
import hu.schonherz.administration.persistence.dao.CourierIncomeDao;
import hu.schonherz.administration.persistence.dao.RestaurantDao;
import hu.schonherz.administration.persistence.dao.UserDao;
import hu.schonherz.administration.persistence.dao.helper.CargoSpecification;
import hu.schonherz.administration.persistence.dao.helper.CourierIncomeSpecification;
import hu.schonherz.administration.persistence.entities.Cargo;
import hu.schonherz.administration.persistence.entities.CourierIncome;
import hu.schonherz.administration.persistence.entities.helper.State;
import hu.schonherz.administration.service.converter.CargoConverter;
import hu.schonherz.administration.service.converter.CourierIncomeConverter;
import hu.schonherz.administration.service.converter.UserConverter;
import hu.schonherz.administration.service.validator.CargoValidator;
import hu.schonherz.administration.serviceapi.RemoteCargoService;
import hu.schonherz.administration.serviceapi.dto.CargoDTO;
import hu.schonherz.administration.serviceapi.dto.CargoState;
import hu.schonherz.administration.serviceapi.dto.CourierIncomeDTO;
import hu.schonherz.administration.serviceapi.dto.DeliveryStateServ;
import hu.schonherz.administration.serviceapi.dto.ItemQuantityDTO;
import hu.schonherz.administration.serviceapi.dto.OrderDTO;
import hu.schonherz.administration.serviceapi.dto.PaymentMethod;
import hu.schonherz.administration.serviceapi.dto.RoleDTO;
import hu.schonherz.administration.serviceapi.dto.UserDTO;
import hu.schonherz.administration.serviceapi.exeption.BusyCourierException;
import hu.schonherz.administration.serviceapi.exeption.CargoAlreadyTakenException;
import hu.schonherz.administration.serviceapi.exeption.CargoNotFoundException;
import hu.schonherz.administration.serviceapi.exeption.CourierNotFoundException;
import hu.schonherz.administration.serviceapi.exeption.IllegalStateTransitionException;
import hu.schonherz.administration.serviceapi.exeption.InvalidDateException;
import hu.schonherz.administration.serviceapi.exeption.InvalidFieldValuesException;
import hu.schonherz.administration.serviceapi.exeption.NotAllOrderCompletedException;

@Stateless(mappedName = "RemoteCargoService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
@Local(RemoteCargoService.class)
public class RemoteCargoServiceImpl implements RemoteCargoService {

	@Autowired
	private CargoDao cargoDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private RestaurantDao restaurantDao;

	private CargoConverter cv;

	@Autowired
	private CourierIncomeDao incomeDao;

	private CourierIncomeConverter incomeConverter;

	@Override
	public CargoDTO saveCargo(CargoDTO cargo) throws InvalidFieldValuesException {
		if (CargoValidator.isValidNewCargo(cargo)) {
			Cargo cargoEntity = cv.toEntity(cargo);
			Cargo cargoEntity2 = cargoDao.save(cargoEntity);
			return cv.toDTO(cargoEntity2);
		} else {
			return null;
		}

	}

	@Override
	public List<CargoDTO> getCargos() {
		return cv.toDTO(cargoDao.findAll());
	}

	public CargoDao getCargoDao() {
		return cargoDao;
	}

	public void setCargoDao(CargoDao cargoDao) {
		this.cargoDao = cargoDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public RestaurantDao getRestaurantDao() {
		return restaurantDao;
	}

	public void setRestaurantDao(RestaurantDao restaurantDao) {
		this.restaurantDao = restaurantDao;
	}

	@PostConstruct
	void init() {
		incomeConverter = new CourierIncomeConverter();
		incomeConverter.setUserDao(userDao);
		cv = new CargoConverter();
		cv.setRestaurantDao(restaurantDao);
		cv.setUserDao(userDao);

	}

	public CargoConverter getCv() {
		return cv;
	}

	public void setCv(CargoConverter cv) {
		this.cv = cv;
	}

	@Override
	public List<CargoDTO> getCargosByDate(Date date) throws InvalidDateException {
		if (date.after(new Date())) {
			InvalidDateException ex = new InvalidDateException();
			ex.setMessage("Future dates are not supported.");
			throw ex;
		}
		List<Cargo> c2 = cargoDao.findAll(CargoSpecification.lastModifiedAt(date));
		return cv.toDTO(c2);
	}

	public void assignCargoToCourier(Long cargoID, Long courierID) throws CargoAlreadyTakenException,
			CargoNotFoundException, CourierNotFoundException, BusyCourierException, InvalidFieldValuesException {
		UserDTO courier = UserConverter.toVo(userDao.findOne(courierID));
		if (courier == null)
			throw new CourierNotFoundException();
		if (!isCourier(courier))
			throw new CourierNotFoundException();

		List<CargoDTO> cargosTakenByCourier = cv.toDTO(cargoDao.findByCourier(UserConverter.toEntity(courier)));
		if (cargosTakenByCourier != null) {
			if (!cargosTakenByCourier.isEmpty()) {
				for (CargoDTO cargoDTO : cargosTakenByCourier)
					if (cargoDTO.getState().equals(State.Delivering) || cargoDTO.getState().equals(State.Taken))
						throw new BusyCourierException();
			}
		}

		CargoDTO cargoDTO = cv.toDTO(cargoDao.findOne(cargoID));
		if (cargoDTO == null) {
			throw new CargoNotFoundException();
		}
		if (cargoDTO.getCourierId() != null) {
			throw new CargoAlreadyTakenException();
		}

		cargoDTO.setCourierId(courierID);
		cargoDTO.setState(CargoState.Taken);
		cargoDao.save(cv.toEntity(cargoDTO));
		incomeDao.save(incomeConverter.toEntity(createIncomeFromCargo(cargoDTO)));
	}

	@Override
	public void changeCargoState(long cargoId, long courierId, CargoState local)
			throws CargoNotFoundException, CargoAlreadyTakenException, IllegalStateTransitionException,
			CourierNotFoundException, NotAllOrderCompletedException, InvalidFieldValuesException {
		if (local.equals(CargoState.Taken) || local.equals(CargoState.Free))
			throw new IllegalStateTransitionException();

		CargoDTO cargoDTO = cv.toDTO(cargoDao.findOne(cargoId));
		UserDTO courierDTO = UserConverter.toVo(userDao.findOne(courierId));

		if (courierDTO == null) {
			throw new CourierNotFoundException();
		}

		if (cargoDTO == null) {
			throw new CargoNotFoundException();
		}

		if (cargoDTO.getCourierId() == null) {
			throw new IllegalStateTransitionException();
		}

		if (cargoDTO.getCourierId() != courierId) {
			throw new CargoAlreadyTakenException();
		}

		if (!isCourier(courierDTO)) {
			throw new CourierNotFoundException();
		}

		if (cargoDTO.getState().equals(State.Delivered)) {
			throw new IllegalStateTransitionException();
		}
		if (local.equals(CargoState.Delivered) && cargoDTO.getState().equals(CargoState.Taken)) {
			throw new IllegalStateTransitionException();
		}

		if (local.equals(CargoState.Delivered))
			for (OrderDTO order : cargoDTO.getOrders()) {
				if (order.getDeliveryState().equals(DeliveryStateServ.Failed)
						|| order.getDeliveryState().equals(DeliveryStateServ.In_progress)) {
					throw new NotAllOrderCompletedException();
				}

			}

		cargoDTO.setState(local);
		cargoDao.save(cv.toEntity(cargoDTO));

	}

	private boolean isCourier(UserDTO user) {
		for (RoleDTO role : user.getRoles()) {
			if (role.getName().equals("ROLE_COURIER")) {
				return true;
			}
		}
		return false;
	}

	private CourierIncomeDTO createIncomeFromCargo(CargoDTO cargo) {
		UserDTO courier = UserConverter.toVo(userDao.findOne(cargo.getCourierId()));
		List<CargoDTO> cargos = getCargosOfCourierAtDate(courier, new Date());
		CourierIncomeDTO income = null;

		int creditCard = 0;
		int cash = 0;
		int voucher = 0;
		int SZÉPCard = 0;
		for (OrderDTO order : cargo.getOrders()) {
			PaymentMethod p = order.getPayment();
			switch (p) {
			case Cash:
				cash += totalCostOf(order);
				break;
			case VOUCHER:
				voucher += totalCostOf(order);
				break;
			case SZÉPCard:
				SZÉPCard += totalCostOf(order);
				break;
			case CreditCard:
				creditCard += totalCostOf(order);
				break;
			}
		}

		if (cargos.size() == 1) {
			income = new CourierIncomeDTO();
			income.setCash(cash);
			income.setCourierId(courier.getId());
			income.setCourierName(courier.getName());
			income.setCrediCard(creditCard);
			income.setDate(new Date());
			income.setSZÉPCard(SZÉPCard);
			income.setVoucher(voucher);

		} else {
			income = getIncomeOfCourierAtDate(courier, new Date());
			if (income == null)
				income = new CourierIncomeDTO();
			if (income.getCash() != null)
				income.setCash(income.getCash() + cash);
			else
				income.setCash(cash);
			income.setCourierId(courier.getId());
			income.setCourierName(courier.getName());

			if (income.getCrediCard() != null)
				income.setCrediCard(income.getCrediCard() + creditCard);
			else
				income.setCrediCard(creditCard);

			income.setDate(new Date());

			if (income.getVoucher() != null)
				income.setVoucher(income.getVoucher() + voucher);
			else
				income.setVoucher(voucher);

			if (income.getSZÉPCard() != null)
				income.setSZÉPCard(income.getSZÉPCard() + SZÉPCard);
			else
				income.setSZÉPCard(SZÉPCard);

		}
		return income;
	}

	private Integer totalCostOf(OrderDTO order) {
		Integer totalCost = 0;
		for (ItemQuantityDTO i : order.getItems()) {
			totalCost = i.getItem().getPrice() * i.getQuantity();
		}
		return totalCost;
	}

	private List<CargoDTO> getCargosOfCourierAtDate(UserDTO courier, Date date) {
		List<CargoDTO> cargos = null;
		Specification<Cargo> dateSpec = CargoSpecification.lastModifiedAt(date);
		Specification<Cargo> userSpec = CargoSpecification.takenBy(UserConverter.toEntity(courier));
		cargos = cv.toDTO(cargoDao.findAll(Specifications.where(dateSpec).and(userSpec)));
		return cargos;
	}

	/*
	 * This method returns the one CourierIncome by date. Note that though a
	 * list is returned by dao the actual amount of income rows by day for a
	 * specific courier should only be 0 or 1;
	 */
	private CourierIncomeDTO getIncomeOfCourierAtDate(UserDTO courier, Date date) {
		List<CourierIncomeDTO> income = null;
		Specification<CourierIncome> dateSpec = CourierIncomeSpecification.lastModifiedAt(date);
		Specification<CourierIncome> userSpec = CourierIncomeSpecification.takenBy(UserConverter.toEntity(courier));
		income = incomeConverter.toDTO(incomeDao.findAll(Specifications.where(dateSpec).and(userSpec)));
		if (income != null && !income.isEmpty())
			return income.get(0);
		else
			return null;
	}
}
