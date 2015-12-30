package hu.schonherz.administration.service.validator;

import hu.schonherz.administration.serviceapi.dto.CargoDTO;
import hu.schonherz.administration.serviceapi.dto.CargoState;
import hu.schonherz.administration.serviceapi.dto.OrderDTO;
import hu.schonherz.administration.serviceapi.exeption.InvalidFieldValuesException;

/**
 * 
 * @author Miklós Kosárkár This class validates a cargo. If it's invalid an
 *         InvalidFieldValuesException is thrown.
 */
public class CargoValidator {

	/**
	 * 
	 * @param cargo
	 * @return true if the cargo is valid
	 * @throws InvalidFieldValuesException
	 */
	public static boolean isValidCargo(CargoDTO cargo) throws InvalidFieldValuesException {
		String err = null;
		if (cargo.getCourierId() != null) {
			err += "Courier id should not be set. " + cargo.getCourierId();
		}

		if (cargo.getState() == null) {
			err += "Cargo state should be 'Free'. ";
		} else {
			if (!cargo.getState().equals(CargoState.Free)) {
				err += "Cargo state should be 'Free' and not: " + cargo.getState().toString();
			}
		}

		if (cargo.getOrders() == null) {
			err += "Cargo orders should not be empty. ";
		} else {
			if (cargo.getOrders().isEmpty()) {
				err += "Cargo orders should not be empty. ";
			}
		}
		for (OrderDTO order : cargo.getOrders()) {
			if (order.getAddressToDeliver() == null) {
				err += "Order address should not be empty. ";
			} else {
				if (order.getAddressToDeliver().isEmpty()) {
					err += "Order address should not be empty. ";
				}
			}

			if (order.getState() == null) {
				err += "New orders should have the state 'Free'.";
			} else {
				if (!order.getState().equals(CargoState.Free)) {
					err += "New orders should have the state 'Free'.";
				}
			}
		}
		

		if (err == null) {
			return true;
		} else {
			InvalidFieldValuesException exception = new InvalidFieldValuesException();
			exception.setMessage(err);
			throw exception;
		}
	}

}
