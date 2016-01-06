package hu.schonherz.administration.serviceapi.exeption;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class CargoNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1771411175256516811L;

}
