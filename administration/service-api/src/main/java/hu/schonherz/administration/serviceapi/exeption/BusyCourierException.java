package hu.schonherz.administration.serviceapi.exeption;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class BusyCourierException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5426492509543323180L;

}
