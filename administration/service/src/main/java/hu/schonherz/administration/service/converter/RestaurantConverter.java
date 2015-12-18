package hu.schonherz.administration.service.converter;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.schonherz.administration.persistence.entities.Restaurant;
import hu.schonherz.administration.serviceapi.dto.RestaurantDTO;

public class RestaurantConverter {

	static Mapper mapper = new DozerBeanMapper();

	public static RestaurantDTO toDTO(Restaurant restaurant) {
		if (restaurant == null) {
			return null;
		}
		return mapper.map(restaurant, RestaurantDTO.class);
	}

	public static Restaurant toEntity(RestaurantDTO restaurantDTO) {
		if (restaurantDTO == null) {
			return null;
		}
		return mapper.map(restaurantDTO, Restaurant.class);
	}

	public static List<RestaurantDTO> toDTO(List<Restaurant> restaurant) {
		List<RestaurantDTO> rv = new ArrayList<>();
		for (Restaurant restaurants : restaurant) {
			rv.add(toDTO(restaurants));
		}
		return rv;
	}

	public static List<Restaurant> toEntity(List<RestaurantDTO> restaurant) {
		List<Restaurant> rv = new ArrayList<>();
		for (RestaurantDTO restaurants : restaurant) {
			rv.add(toEntity(restaurants));
		}
		return rv;
	}
}
