package hu.schonherz.administration.service.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hu.schonherz.administration.persistence.dao.RestaurantDao;
import hu.schonherz.administration.persistence.dao.UserDao;
import hu.schonherz.administration.persistence.entities.Cargo;
import hu.schonherz.administration.persistence.entities.Restaurant;
import hu.schonherz.administration.persistence.entities.Role;
import hu.schonherz.administration.persistence.entities.User;
import hu.schonherz.administration.serviceapi.dto.CargoDTO;
import hu.schonherz.administration.serviceapi.exeption.InvalidFieldValuesException;


public class CargoConverter {

	private RestaurantDao restaurantDao;
	
	private UserDao userDao;
	
	private CargoStateConverter cargoStateConverter = new CargoStateConverter();
	
	public CargoDTO toDTO(Cargo cargo){
		CargoDTO result = new CargoDTO();
		if(cargo.getCourier()!=null){
		result.setCourierId(cargo.getCourier().getId());
		result.setCourierName(cargo.getCourier().getName());
		}
		result.setId(cargo.getId());
		result.setDate(cargo.getDate());
		result.setOrders(OrderConverter.toDTO(cargo.getOrders()));
		
		if(cargo.getRestaurant()!=null){
		result.setRestaurantId(cargo.getRestaurant().getId());
		}
		result.setState(this.cargoStateConverter.toDTO( cargo.getState()));
		result.setIsDeleted(cargo.getIsDeleted());
		return result;
	}
	
	public Cargo toEntity(CargoDTO cargo) throws InvalidFieldValuesException{
		Cargo result = new Cargo();
		result.setId(cargo.getId());
		result.setDate(new Date());
		if(cargo.getCourierId()==null){
			result.setCourier(null);
		}else{
			Long id = cargo.getCourierId();
			User courier =  userDao.findOne(id);
			if(courier==null){
				InvalidFieldValuesException exception = new InvalidFieldValuesException();
				exception.setMessage("Not a courier id");	
				throw exception;
			}
			Boolean isCourier = false;
			for(Role r: courier.getRoles()){
				if(r.getName().equals("ROLE_COURIER")){
					isCourier = true;
				}
			}
			if(isCourier){
				result.setCourier(courier);
			}else{
				InvalidFieldValuesException exception = new InvalidFieldValuesException();
				exception.setMessage("Not a courier id");
				throw exception;
			}
			
		}
		if(cargo.getRestaurantId()==null){
			InvalidFieldValuesException exception = new InvalidFieldValuesException();
			exception.setMessage("Invalid restaurant id");
			throw exception;
		}else{
			Long id = cargo.getRestaurantId();
			Restaurant rest = restaurantDao.findOne(id);
			if(rest==null){
				InvalidFieldValuesException exception = new InvalidFieldValuesException();
				exception.setMessage("Invalid restaurant id");
				throw exception;
			}
			result.setRestaurant(rest);
		}
		result.setIsDeleted(cargo.getIsDeleted());
		result.setOrders(OrderConverter.toEntity(cargo.getOrders()));
		result.setState(this.cargoStateConverter.toEntity(cargo.getState()));
		return result;
	}
	
	public List<CargoDTO> toDTO(List<Cargo> cargo) {
		List<CargoDTO> rv = new ArrayList<>();
		for (Cargo cargos : cargo) {
			rv.add(toDTO(cargos));
		}
		return rv;
	}

	public List<Cargo> toEntity(List<CargoDTO> cargo) throws InvalidFieldValuesException {
		List<Cargo> rv = new ArrayList<>();
		for (CargoDTO cargos : cargo) {
			rv.add(toEntity(cargos));
		}
		return rv;
	}

	public RestaurantDao getRestaurantDao() {
		return restaurantDao;
	}

	public void setRestaurantDao(RestaurantDao restaurantDao) {
		this.restaurantDao = restaurantDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
