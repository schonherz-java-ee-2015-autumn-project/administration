package hu.schonherz.administration.serviceapi.exeption;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class OrderNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4677686081549211701L;

}
