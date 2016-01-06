package hu.schonherz.administration.serviceapi.exeption;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class CourierNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5141496867856699209L;

}
