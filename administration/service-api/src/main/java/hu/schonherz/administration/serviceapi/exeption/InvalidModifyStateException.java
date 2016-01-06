package hu.schonherz.administration.serviceapi.exeption;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class InvalidModifyStateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1005355273997683732L;

}
