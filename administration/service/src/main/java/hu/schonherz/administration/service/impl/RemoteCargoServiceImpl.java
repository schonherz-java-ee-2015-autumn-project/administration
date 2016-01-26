package hu.schonherz.administration.service.impl;

import java.util.ArrayList;
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
import hu.schonherz.administration.persistence.dao.IncomeReportDao;
import hu.schonherz.administration.persistence.dao.ItemQuantityDao;
import hu.schonherz.administration.persistence.dao.OrderDao;
import hu.schonherz.administration.persistence.dao.RestaurantDao;
import hu.schonherz.administration.persistence.dao.RestaurantReportDao;
import hu.schonherz.administration.persistence.dao.UserDao;
import hu.schonherz.administration.persistence.dao.helper.CargoSpecification;
import hu.schonherz.administration.persistence.dao.helper.CourierIncomeSpecification;
import hu.schonherz.administration.persistence.dao.helper.IncomeReportSpecification;
import hu.schonherz.administration.persistence.dao.helper.RestaurantReportSpecification;
import hu.schonherz.administration.persistence.entities.Cargo;
import hu.schonherz.administration.persistence.entities.CourierIncome;
import hu.schonherz.administration.persistence.entities.IncomeReport;
import hu.schonherz.administration.persistence.entities.RestaurantReport;
import hu.schonherz.administration.persistence.entities.Role;
import hu.schonherz.administration.persistence.entities.User;
import hu.schonherz.administration.persistence.entities.helper.State;
import hu.schonherz.administration.service.converter.CargoConverter;
import hu.schonherz.administration.service.converter.CargoStateConverter;
import hu.schonherz.administration.service.converter.CourierIncomeConverter;
import hu.schonherz.administration.service.converter.IncomeReportConverter;
import hu.schonherz.administration.service.converter.ItemQuantityConverter;
import hu.schonherz.administration.service.converter.OrderConverter;
import hu.schonherz.administration.service.converter.RestaurantConverter;
import hu.schonherz.administration.service.converter.RestaurantReportConverter;
import hu.schonherz.administration.service.converter.UserConverter;
import hu.schonherz.administration.service.validator.CargoValidator;
import hu.schonherz.administration.serviceapi.RemoteCargoService;
import hu.schonherz.administration.serviceapi.dto.CargoDTO;
import hu.schonherz.administration.serviceapi.dto.CargoState;
import hu.schonherz.administration.serviceapi.dto.CourierIncomeDTO;
import hu.schonherz.administration.serviceapi.dto.DeliveryStateServ;
import hu.schonherz.administration.serviceapi.dto.IncomeReportDTO;
import hu.schonherz.administration.serviceapi.dto.ItemQuantityDTO;
import hu.schonherz.administration.serviceapi.dto.OrderDTO;
import hu.schonherz.administration.serviceapi.dto.PaymentMethod;
import hu.schonherz.administration.serviceapi.dto.RestaurantDTO;
import hu.schonherz.administration.serviceapi.dto.RestaurantReportDTO;
import hu.schonherz.administration.serviceapi.dto.RoleDTO;
import hu.schonherz.administration.serviceapi.dto.UserDTO;
import hu.schonherz.administration.serviceapi.exeption.AddressNotFoundException;
import hu.schonherz.administration.serviceapi.exeption.BusyCourierException;
import hu.schonherz.administration.serviceapi.exeption.CargoAlreadyTakenException;
import hu.schonherz.administration.serviceapi.exeption.CargoNotFoundException;
import hu.schonherz.administration.serviceapi.exeption.CourierNotFoundException;
import hu.schonherz.administration.serviceapi.exeption.IllegalStateTransitionException;
import hu.schonherz.administration.serviceapi.exeption.InvalidDateException;
import hu.schonherz.administration.serviceapi.exeption.InvalidFieldValuesException;
import hu.schonherz.administration.serviceapi.exeption.InvalidModifyStateException;
import hu.schonherz.administration.serviceapi.exeption.ModifyWithoutIdException;
import hu.schonherz.administration.serviceapi.exeption.NotAllOrderCompletedException;
import hu.schonherz.administration.serviceapi.exeption.OrderException;
import hu.schonherz.administration.serviceapi.exeption.OrderNotFoundException;

@Stateless(mappedName = "RemoteCargoService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
@Local(RemoteCargoService.class)
public class RemoteCargoServiceImpl implements RemoteCargoService {

	@Autowired
	private ItemQuantityDao itemQuantityDao;

	@Autowired
	private CargoDao cargoDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private RestaurantDao restaurantDao;
	
	@Autowired
	private RestaurantReportDao restaurantReportDao;

	private CargoConverter cv;

	@Autowired
	private CourierIncomeDao incomeDao;

	@Autowired
	private IncomeReportDao incomeReportDao;

	private CourierIncomeConverter incomeConverter;
	
	private CargoStateConverter cargoStateConverter ;
	
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
				if (order.getDeliveryState().equals(DeliveryStateServ.In_progress)) {
					throw new NotAllOrderCompletedException();
				}

			}

		cargoDTO.setState(local);
		cargoDao.save(cv.toEntity(cargoDTO));
		if (cargoDTO.getState().equals(CargoState.Delivered)) {
			incomeDao.save(incomeConverter.toEntity(createIncomeFromCargo(cargoDTO)));
			incomeReportDao.save(IncomeReportConverter.toEntity(updateReportTable(cargoDTO)));
			restaurantReportDao.save(RestaurantReportConverter.toEntity(updateRestaurantReport(cargoDTO)));
		}

	}
	private RestaurantReportDTO updateRestaurantReport(CargoDTO cargo){
		RestaurantReportDTO restaurantReport = null;
		int totalAmount = 0, cash = 0 ,voucher = 0,szepCard = 0,creditCard = 0;
		float restaurantTransferAmount = 0;
		PaymentMethod p = null;
		RestaurantDTO restaurant = RestaurantConverter.toDTO(restaurantDao.findOne(cargo.getRestaurantId()));
		Specification<RestaurantReport> dateSpec = RestaurantReportSpecification.lastModifiedAt(new Date());
		Specification<RestaurantReport> restaurantSpec = RestaurantReportSpecification.restaurantName(restaurant.getName());
		try{
			restaurantReport = RestaurantReportConverter.toDTO(restaurantReportDao.findOne(Specifications.where(dateSpec).and(restaurantSpec)));
		}catch(NullPointerException e){
			restaurantReport = new RestaurantReportDTO();
			restaurantReport.setDefault();
		}
		for (OrderDTO order : cargo.getOrders()) {
			p = order.getPayment();
			switch (p) {
			case Cash:
				cash += totalCostOf(order);
				break;
			case VOUCHER:
				voucher += totalCostOf(order);
				break;
			case SZEPCard:
				szepCard += totalCostOf(order);
				break;
			case CreditCard:
				creditCard += totalCostOf(order);
				break;
			}
			totalAmount += totalCostOf(order);
		}
		float priceInProcent = (float)restaurant.getPrice() / 100;
		float servicesPrize  =  totalAmount * priceInProcent;
		restaurantTransferAmount = totalAmount - servicesPrize;
		restaurantReport.setCash(restaurantReport.getCash() + cash);
		restaurantReport.setCreditCard(restaurantReport.getCreditCard() + creditCard);
		restaurantReport.setRestaurant(restaurant.getName());
		restaurantReport.setRestaurantPrice(restaurant.getPrice());
		restaurantReport.setRestaurantTransferAmount(restaurantReport.getRestaurantTransferAmount() + restaurantTransferAmount);
		restaurantReport.setSzepCard(restaurantReport.getSzepCard() + szepCard);
		restaurantReport.setTotalAmount(restaurantReport.getTotalAmount() + totalAmount);
		restaurantReport.setVoucher(restaurantReport.getVoucher() + voucher);
		restaurantReport.setServicesPrize(restaurantReport.getServicesPrize() + servicesPrize);
		
		return restaurantReport;
	}
	
	private boolean isCourier(UserDTO user) {
		for (RoleDTO role : user.getRoles()) {
			if (role.getName().equals("ROLE_COURIER")) {
				return true;
			}
		}
		return false;
	}

	private IncomeReportDTO updateReportTable(CargoDTO cargo) {

		Specification<IncomeReport> dateSpec = IncomeReportSpecification.lastModifiedAt(new Date());
		RestaurantDTO restaurant = RestaurantConverter.toDTO(restaurantDao.findOne(cargo.getRestaurantId()));
		IncomeReportDTO income = null;
		PaymentMethod p = null;
		int totalAmount = 0, cash = 0 ,voucher = 0,szepCard = 0,creditCard = 0;
		float CourierServiceAmount = 0;
		
		try {
			income = IncomeReportConverter.toDTO(incomeReportDao.findOne(Specifications.where(dateSpec)));
		} catch (NullPointerException e) {
			income = new IncomeReportDTO();
			income.setDefault();
		}
		for (OrderDTO order : cargo.getOrders()) {
			p = order.getPayment();
			switch (p) {
			case Cash:
				cash += totalCostOf(order);
				break;
			case VOUCHER:
				voucher += totalCostOf(order);
				break;
			case SZEPCard:
				szepCard += totalCostOf(order);
				break;
			case CreditCard:
				creditCard += totalCostOf(order);
				break;
			}
			totalAmount += totalCostOf(order);
		}
		float e = (float)restaurant.getPrice() / 100;
		CourierServiceAmount = totalAmount * e;
		income.setTotalAmount(income.getTotalAmount() + totalAmount);
		income.setCreditCard(income.getCreditCard() + creditCard);
		income.setSzepCard(income.getSzepCard() + szepCard);
		income.setVoucher(income.getVoucher() + voucher);
		income.setCash(income.getCash() + cash);
		income.setCourierServiceAmount(income.getCourierServiceAmount() +CourierServiceAmount);
		income.setRestaurantTransferAmount(income.getRestaurantTransferAmount() + (totalAmount - CourierServiceAmount));
		
		return income;
	}

	private CourierIncomeDTO createIncomeFromCargo(CargoDTO cargo) {
		UserDTO courier = UserConverter.toVo(userDao.findOne(cargo.getCourierId()));
		List<CargoDTO> cargos = getCargosOfCourierAtDate(courier, new Date());
		CourierIncomeDTO income = null;

		int creditCard = 0;
		int cash = 0;
		int voucher = 0;
		int SZEPCard = 0;
		for (OrderDTO order : cargo.getOrders()) {
			PaymentMethod p = order.getPayment();
			switch (p) {
			case Cash:
				cash += totalCostOf(order);
				break;
			case VOUCHER:
				voucher += totalCostOf(order);
				break;
			case SZEPCard:
				SZEPCard += totalCostOf(order);
				break;
			case CreditCard:
				creditCard += totalCostOf(order);
				break;
			}
		}

		if (cargos.size() == 1) {
			income = new CourierIncomeDTO();
			income.setActualCash(0);
			income.setActualVoucher(0);
			income.setCash(cash);
			income.setCourierId(courier.getId());
			income.setCourierName(courier.getName());
			income.setCrediCard(creditCard);
			income.setDate(new Date());
			income.setSzepCard(SZEPCard);
			income.setVoucher(voucher);

		} else {
			income = getIncomeOfCourierAtDate(courier, new Date());
			if (income == null) {
				income = new CourierIncomeDTO();
				income.setActualCash(0);
				income.setActualVoucher(0);
			}
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

			if (income.getSzepCard() != null)
				income.setSzepCard(income.getSzepCard() + SZEPCard);
			else
				income.setSzepCard(SZEPCard);
		}
		return income;
	}

	private Integer totalCostOf(OrderDTO order) {
		Integer totalCost = 0;
		if (order.getDeliveryState().equals(DeliveryStateServ.Delivered))
			for (ItemQuantityDTO i : order.getItems()) {
				totalCost += i.getItem().getPrice() * i.getQuantity();
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

	@Override
	public void changePaymentState(Long courierId, Long orderId, PaymentMethod paymentMethod)
			throws CourierNotFoundException, CargoNotFoundException, OrderException, AddressNotFoundException {

		UserDTO courierDTO = UserConverter.toVo(userDao.findOne(courierId));
		if (courierDTO == null)
			throw new CourierNotFoundException();

		
		boolean isOrderBelongsToCourier = false;
		
		if (!isCourier(courierDTO))
			throw new CourierNotFoundException();

		List<CargoDTO> cargoesDTO = cv.toDTO(cargoDao.findByCourier(UserConverter.toEntity(courierDTO)));
		if (cargoesDTO == null)
			throw new CargoNotFoundException();

		OrderDTO ord = OrderConverter.toDTO(orderDao.findOne(orderId));
		if (ord == null) {
			throw new AddressNotFoundException("Address does not exist");
		}

		for (CargoDTO c : cargoesDTO) {
			for (OrderDTO orderDTO : c.getOrders()) {
				if (orderDTO.getId().equals(orderId)) {
					if (!(c.getState().equals(CargoState.Delivering))) {
						throw new OrderException("Cargo state is not delivering");
					}
					if (!(orderDTO.getDeliveryState().equals(DeliveryStateServ.In_progress))) {
						throw new OrderException("This order state is not In_progress");
					}
					isOrderBelongsToCourier = true;
					orderDTO.setPayment(paymentMethod);
					orderDao.save(OrderConverter.toEntity(orderDTO));
					break;
				}
			}
		}
		if (!isOrderBelongsToCourier) {
			throw new OrderException("This order is not belongs to this courier");
		}

	}

	@Override
	public CargoDTO getActiveCargoByCourier(long courierId) throws CourierNotFoundException, AddressNotFoundException {
		User courier = userDao.findById(courierId);
		if (courier != null && isCourier(UserConverter.toVo(courier))) {
			Specification<Cargo> today = CargoSpecification.lastModifiedAt(new Date());
			Specification<Cargo> takenBy = CargoSpecification.takenBy(courier);
			Specification<Cargo> isActive = CargoSpecification.isActive();
			List<Cargo> cargo = cargoDao.findAll(Specifications.where(today).and(takenBy).and(isActive));
			if (cargo != null && !cargo.isEmpty()) {
				return cv.toDTO(cargo.get(0));
			} else {
				throw new AddressNotFoundException("Courier " + courier.getName() + " has no active cargos.");
			}
		} else {
			throw new CourierNotFoundException();
		}
	}

	@Override
	public CargoDTO modifyCargo(CargoDTO cargo) throws CargoNotFoundException, InvalidFieldValuesException,
			ModifyWithoutIdException, OrderNotFoundException, InvalidModifyStateException {
		Cargo cargoEntity = null;
		if (cargo.getId() != null) {
			cargoEntity = cargoDao.findOne(cargo.getId());
			if (!cargoEntity.getState().equals(cargoStateConverter.toEntity(CargoState.Free))) {
				throw new InvalidModifyStateException();
			}
		} else {
			throw new ModifyWithoutIdException();
		}

		if (cargoEntity == null) {
			throw new CargoNotFoundException();
		} else {
			if (CargoValidator.isValidNewCargo(cargo)) {
				List<OrderDTO> orders = new ArrayList<>();
				for (OrderDTO order : cargo.getOrders()) {
					orders.add(modifyOrder(order));
				}
				cargo.setOrders(orders);
				cargoDao.save(cv.toEntity(cargo));
				return cargo;
			}
		}

		return null;
	}

	private OrderDTO modifyOrder(OrderDTO order) throws ModifyWithoutIdException, OrderNotFoundException {
		List<ItemQuantityDTO> iqDTO = new ArrayList<>();
		for (ItemQuantityDTO items : order.getItems()) {
			iqDTO.add(ItemQuantityConverter.toDTO(itemQuantityDao.save(ItemQuantityConverter.toEntity(items))));
		}
		order.setItems(iqDTO);
		orderDao.save(OrderConverter.toEntity(order));
		return order;
	}

	public ItemQuantityDao getItemQuantityDao() {
		return itemQuantityDao;
	}

	public void setItemQuantityDao(ItemQuantityDao itemQuantityDao) {
		this.itemQuantityDao = itemQuantityDao;
	}
}
