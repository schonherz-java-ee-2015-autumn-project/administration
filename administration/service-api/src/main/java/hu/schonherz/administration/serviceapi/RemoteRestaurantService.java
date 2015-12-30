package hu.schonherz.administration.serviceapi;

import hu.schonherz.administration.serviceapi.dto.RestaurantDTO;

public interface RemoteRestaurantService {

	public RestaurantDTO findById(Long id);
}
