package hu.schonherz.administration.web.restaurant.employee;

import java.util.ArrayList;
import java.util.List;

import hu.schonherz.administration.serviceapi.RestaurantService;
import hu.schonherz.administration.serviceapi.dto.UserDTO;

public class EmployeeConverter {	
	
	public static UserDTO  employeeToDTO(RestaurantEmployee employee){
		UserDTO user = new UserDTO();
		user.setId(employee.getId());
		user.setName(employee.getName());
		user.setPassword(employee.getPassword());
		user.setUsername(employee.getUsername());
		user.setPhoneNumber(employee.getPhoneNumber());
		user.setRoles(employee.getRoles());
		return user;
	}
	
	public  static RestaurantEmployee  dtoToEmployee(UserDTO employee){
		RestaurantEmployee user = new RestaurantEmployee();
		user.setId(employee.getId());
		user.setName(employee.getName());
		user.setPassword(employee.getPassword());
		user.setUsername(employee.getUsername());
		user.setPhoneNumber(employee.getPhoneNumber());
		user.setRoles(employee.getRoles());
		return user;
	}
	
	public static List<RestaurantEmployee> dtoListToEmployee(List<UserDTO> users, RestaurantService service){
		List<RestaurantEmployee> result = new ArrayList<>();
		for(UserDTO user: users){
			RestaurantEmployee e =  dtoToEmployee(user);
			e.setRestaurant(service.findRestaurantByUser(user));
			result.add(e);
		}
		return result;
	}
 
}
