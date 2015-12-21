package hu.schonherz.administration.web.restaurant.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hu.schonherz.administration.serviceapi.dto.RestaurantDTO;

public class RestaurantValidator {

	public static boolean isValidRestaurant(RestaurantDTO restaurant){
		if (isValidAddress(restaurant.getAddress()) && isValidName(restaurant.getName())
				&& isValidPhoneNumber(restaurant.getPhoneNumber()) && isValidPrice(restaurant.getPrice())) {
			return true;
		}else{
			return false;
		}
		
	}

	private static boolean isValidName(String name) {
		if (name.length() < 3 && name.length() > 150)
			return false;

		if (!name.isEmpty()) {
			Pattern p = Pattern.compile("[\\w [0-9]-]+", Pattern.UNICODE_CHARACTER_CLASS);
			Matcher m = p.matcher(name);
			if (!m.matches())
				return false;
		}
		return true;
	}

	private static boolean isValidAddress(String address) {
		if (address.length() < 10 && address.length() > 500)
			return false;
		else
			return true;
	}

	private static boolean isValidPhoneNumber(String phoneNumber) {
		if (phoneNumber.length() < 7 && phoneNumber.length() > 11)
			return false;
		else
			return true;
	}

	private static boolean isValidPrice(Integer price) {
		if (price > 0 && price < 99) {
			return true;
		} else {
			return false;
		}
	}
}
