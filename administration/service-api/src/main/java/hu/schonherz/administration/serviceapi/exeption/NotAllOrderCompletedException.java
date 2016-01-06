package hu.schonherz.administration.serviceapi.exeption;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class NotAllOrderCompletedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8900900534164712698L;

}
