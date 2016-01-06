package hu.schonherz.administration.serviceapi.exeption;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class IllegalStateTransitionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7608519209011861977L;

}
