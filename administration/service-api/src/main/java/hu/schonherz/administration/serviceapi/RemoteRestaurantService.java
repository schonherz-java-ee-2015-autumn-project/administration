package hu.schonherz.administration.serviceapi;

import hu.schonherz.administration.serviceapi.dto.RestaurantDTO;
import hu.schonherz.administration.serviceapi.exeption.NoRestaurantAssignedUserException;

public interface RemoteRestaurantService {

	public RestaurantDTO findById(Long id) throws NoRestaurantAssignedUserException;
}
