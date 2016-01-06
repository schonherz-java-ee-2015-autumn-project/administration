package hu.schonherz.administration.serviceapi.exeption;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class CargoAlreadyTakenException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6898807303583725030L;

}
