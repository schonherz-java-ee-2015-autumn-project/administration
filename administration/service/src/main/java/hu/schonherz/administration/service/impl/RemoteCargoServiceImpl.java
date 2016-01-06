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
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.administration.persistence.dao.CargoDao;
import hu.schonherz.administration.persistence.dao.RestaurantDao;
import hu.schonherz.administration.persistence.dao.UserDao;
import hu.schonherz.administration.persistence.dao.helper.CargoSpecification;
import hu.schonherz.administration.persistence.entities.Cargo;
import hu.schonherz.administration.persistence.entities.Order;
import hu.schonherz.administration.persistence.entities.Role;
import hu.schonherz.administration.persistence.entities.User;
import hu.schonherz.administration.persistence.entities.helper.State;
import hu.schonherz.administration.service.converter.CargoConverter;
import hu.schonherz.administration.service.validator.CargoValidator;
import hu.schonherz.administration.serviceapi.RemoteCargoService;
import hu.schonherz.administration.serviceapi.dto.CargoDTO;
import hu.schonherz.administration.serviceapi.dto.OrderDTO;
import hu.schonherz.administration.serviceapi.exeption.BusyCourierException;
import hu.schonherz.administration.serviceapi.exeption.CargoAlreadyTakenException;
import hu.schonherz.administration.serviceapi.exeption.CargoNotFoundException;
import hu.schonherz.administration.serviceapi.exeption.CourierNotFoundException;
import hu.schonherz.administration.serviceapi.exeption.InvalidDateException;
import hu.schonherz.administration.serviceapi.exeption.InvalidFieldValuesException;
import hu.schonherz.administration.serviceapi.exeption.OrderIsNotInProgressException;
import hu.schonherz.administration.serviceapi.exeption.WrongCourierException;

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

	public CargoDTO assignCargoToCourier(Long cargoID, Long courierID)
			throws CargoAlreadyTakenException, CargoNotFoundException, CourierNotFoundException, BusyCourierException {
		User courier = userDao.findOne(courierID);
		if (courier == null)
			throw new CourierNotFoundException();
		boolean isCourier = false;
		for (Role role : courier.getRoles()) {
			if (role.getName().equals("ROLE_COURIER")) {
				isCourier = true;
			}
		}
		if (!isCourier)
			throw new CourierNotFoundException();

		List<Cargo> cargosTakenByCourier = cargoDao.findByCourier(courier);
		if (cargosTakenByCourier != null) {
			if (!cargosTakenByCourier.isEmpty()) {
				throw new BusyCourierException();
			}
		}

		Cargo cargo = cargoDao.findOne(cargoID);
		if (cargo == null) {
			throw new CargoNotFoundException();
		}
		if (cargo.getCourier() != null) {
			throw new CargoAlreadyTakenException();
		}

		cargo.setCourier(courier);
		cargo.setState(State.Taken);

		return cv.toDTO(cargo);
	}
	
	private boolean isCourier(User user){
		for (Role role : user.getRoles()) 
			if (role.getName().equals("ROLE_COURIER"))
				return true;
		return false;
	}
	
	@Override
	public CargoDTO GetCargoByCourier(long courierId) throws CourierNotFoundException {
		User courier;
		courier=userDao.findById(courierId);
		
		if(courier==null||!isCourier(courier))
			throw new CourierNotFoundException();
		if(isCourier(courier)){
			List<Cargo> cargos = cargoDao.findByCourier(courier);
			for(Cargo c:cargos)
				if (c.getState().equals(State.Taken)||c.getState().equals(State.Delivering))
					return cv.toDTO(c);
			
		
		}
		return null;	
	}

	@Override
	public void hasOrderId(long OrderId, long courierId) throws OrderIsNotInProgressException, CourierNotFoundException {
		User courier;
		courier=userDao.findById(courierId);
		if(courier==null||!isCourier(courier))
			throw new CourierNotFoundException();
		if(isCourier(courier)){
			List<Cargo> cargos = cargoDao.findByCourier(courier);
			for(Cargo c:cargos)
				for(Order o:c.getOrders())
					if (o.getId()==OrderId)
						throw new OrderIsNotInProgressException();
		}
				
	}

	@Override
	public void hasOrderId(long OrderId) throws WrongCourierException {
		List<CargoDTO> cargos=getCargos();
		for(CargoDTO c:cargos)
			for(OrderDTO o:c.getOrders())
				if(o.getId()==OrderId)
					throw new WrongCourierException();
		
	}
}
