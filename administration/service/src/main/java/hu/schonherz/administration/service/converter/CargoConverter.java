package hu.schonherz.administration.service.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import hu.schonherz.administration.persistence.dao.RestaurantDao;
import hu.schonherz.administration.persistence.dao.UserDao;
import hu.schonherz.administration.persistence.entities.Cargo;
import hu.schonherz.administration.serviceapi.dto.CargoDTO;

public class CargoConverter {

	@Autowired
	private RestaurantDao restaurantDao;
	
	@Autowired
	private UserDao userDao;
	
	public CargoDTO toDTO(Cargo cargo){
		CargoDTO result = new CargoDTO();
		if(cargo.getCourier()!=null){
		result.setCourierId(cargo.getCourier().getId());
		result.setCourierName(cargo.getCourier().getName());
		result.setCourierPhoneNumber(cargo.getCourier().getPhoneNumber());
		}
		result.setId(cargo.getId());
		
		result.setOrders(OrderConverter.toDTO(cargo.getOrders()));
		
		if(cargo.getRestaurant()!=null){
		result.setRestaurantAddresss(cargo.getRestaurant().getAddress());
		result.setRestaurantId(cargo.getRestaurant().getId());
		result.setRestaurantName(cargo.getRestaurant().getName());
		}
		result.setState(CargoStateConverter.toDTO( cargo.getState()));
		return result;
	}
	
	public Cargo toEntity(CargoDTO cargo){
		Cargo result = new Cargo();
		result.setId(cargo.getId());
		if(cargo.getCourierId()==null){
			result.setCourier(null);
		}else{
			result.setCourier( userDao.findOne(cargo.getCourierId()));
			
		}
		if(cargo.getRestaurantId()==null){
			result.setRestaurant(null);
		}else{
			result.setRestaurant(restaurantDao.findOne(cargo.getRestaurantId()));
		}
		result.setOrders(OrderConverter.toEntity(cargo.getOrders()));
		result.setState(CargoStateConverter.toEntity(cargo.getState()));
		return result;
	}
	
	public List<CargoDTO> toDTO(List<Cargo> cargo) {
		List<CargoDTO> rv = new ArrayList<>();
		for (Cargo cargos : cargo) {
			rv.add(toDTO(cargos));
		}
		return rv;
	}

	public List<Cargo> toEntity(List<CargoDTO> cargo) {
		List<Cargo> rv = new ArrayList<>();
		for (CargoDTO cargos : cargo) {
			rv.add(toEntity(cargos));
		}
		return rv;
	}

}
