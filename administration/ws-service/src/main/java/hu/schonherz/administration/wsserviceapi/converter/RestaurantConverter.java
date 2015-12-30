package hu.schonherz.administration.wsserviceapi.converter;

import java.util.ArrayList;
import java.util.List;

import hu.schonherz.administration.serviceapi.dto.RestaurantDTO;
import hu.schonherz.administration.wsservice.dto.WebRestaurantDTO;

public class RestaurantConverter {

	public static List<WebRestaurantDTO> toWebRestaurantDTO(List<RestaurantDTO> restaurant) {
		List<WebRestaurantDTO> rv = new ArrayList<>();
		for (RestaurantDTO restaurants : restaurant) {
			rv.add(toWebRestaurantDTO(restaurants));
		}
		return rv;
	}

	public static WebRestaurantDTO toWebRestaurantDTO(RestaurantDTO RestaurantDTO) {
		WebRestaurantDTO webUserDTO = new WebRestaurantDTO();

		webUserDTO.setId(RestaurantDTO.getId());
		webUserDTO.setName(RestaurantDTO.getName());
		webUserDTO.setAddress(RestaurantDTO.getAddress());
		webUserDTO.setPhoneNumber(RestaurantDTO.getPhoneNumber());
		webUserDTO.setIsDeleted(RestaurantDTO.getIsDeleted());
		webUserDTO.setPrice(RestaurantDTO.getPrice());

		return webUserDTO;
	}
}
