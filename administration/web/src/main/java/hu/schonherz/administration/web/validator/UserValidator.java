package hu.schonherz.administration.web.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hu.schonherz.administration.serviceapi.dto.UserDTO;

public class UserValidator {

	
	public static boolean isValidUser(UserDTO user){
		if (isValidUsername(user.getUsername()) && isValidName(user.getName())
				&& isValidPhoneNumber(user.getPhoneNumber()) && isValidPassword(user.getPassword())) {
			return true;
		}
		return false;
	}
	public static boolean isValidEditUser(UserDTO user){
		if (isValidUsername(user.getUsername()) && isValidName(user.getName())
				&& isValidPhoneNumber(user.getPhoneNumber())) {
			return true;
		}
		return false;
	}
	
	
	private static boolean isValidName(String name) {
		if (name.length() < 3 || name.length() > 150)
			return false;

		if (!name.isEmpty()) {
			Pattern p = Pattern.compile("[\\w [0-9]-]+", Pattern.UNICODE_CHARACTER_CLASS);
			Matcher m = p.matcher(name);
			if (!m.matches())
				return false;
		}
		return true;
	}

	private static boolean isValidUsername(String username) {
		if (username.length() < 6 || username.length() > 16)
			return false;
		if (!username.isEmpty()) {
			Pattern p = Pattern.compile("[\\w [0-9]-]+", Pattern.UNICODE_CHARACTER_CLASS);
			Matcher m = p.matcher(username);
			if (!m.matches())
				return false;
		}
		return true;
	}

	private static boolean isValidPhoneNumber(String phoneNumber) {
		if (phoneNumber.length() < 7 || phoneNumber.length() > 11)
			return false;
		else
			return true;
	}
	
	private static boolean isValidPassword(String password) {
		if (password.length() < 8 || password.length() > 12)
			return false;
		else
			return true;
	}
}
