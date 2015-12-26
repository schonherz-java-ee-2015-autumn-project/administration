package hu.schonherz.administration.web.restaurant.employee;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import hu.schonherz.administration.serviceapi.RestaurantService;
import hu.schonherz.administration.serviceapi.UserService;
import hu.schonherz.administration.serviceapi.dto.CustomSortOrder;
import hu.schonherz.administration.serviceapi.dto.UserDTO;
import hu.schonherz.administration.serviceapi.dto.UserRole;

@Named
public class LazyRestaurantUser extends LazyDataModel<RestaurantEmployee> {

	@EJB(lookup = "ejb.UserService", beanInterface = UserService.class)
	private UserService userService;

	@EJB(lookup = "ejb.RestaurantService", beanInterface = RestaurantService.class)
	private RestaurantService restaurantService;

	@Override
	public RestaurantEmployee getRowData(String rowKey) {
		return EmployeeConverter.dtoToEmployee(userService.findById(Long.parseLong(rowKey)));
	}

	@Override
	public Object getRowKey(RestaurantEmployee userDTO) {
		return userDTO.getId();
	}

	@Override
	public List<RestaurantEmployee> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		
		CustomSortOrder order;
		if(sortOrder.equals(SortOrder.DESCENDING))
			order = CustomSortOrder.DESC;
		else
			order = CustomSortOrder.ASC;
		
	
		int page = first / pageSize;
		
		if (userService != null) {
			List<UserDTO> list = userService.getUserList(page, pageSize, sortField, order, filters, UserRole.RESTAURANT);
			int rowCount =  userService.getUserCount(filters, UserRole.RESTAURANT);
			if (list == null || list.isEmpty()) {
				return Collections.emptyList();
			} else {
				this.setRowCount(rowCount);
				return EmployeeConverter.dtoListToEmployee(list, restaurantService);
			}
		} else {
			return Collections.emptyList();
		}


	}
}