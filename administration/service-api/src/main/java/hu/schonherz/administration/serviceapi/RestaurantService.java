package hu.schonherz.administration.serviceapi;

import java.util.List;
import java.util.Map;

import hu.schonherz.administration.serviceapi.dto.CustomSortOrder;
import hu.schonherz.administration.serviceapi.dto.RestaurantDTO;

public interface RestaurantService {

	public RestaurantDTO findRestaurantByName(String name);
	
	public RestaurantDTO findById(Long id);

	public void save(RestaurantDTO restaurantDTO);
	
	public void remove(RestaurantDTO restaurantDTO);

	public List<RestaurantDTO> getRestaurants();
	
	
	public List<RestaurantDTO> getRestaurants(int first, int pageSize, String sortField,  CustomSortOrder sortOrder, Map<String, Object> filters);

	public int getRestaurantCount();

	public int getRestaurantCount(Map<String, Object> filters);

	

}
