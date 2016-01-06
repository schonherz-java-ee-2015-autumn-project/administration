package hu.schonherz.administration.serviceapi.exeption;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class ModifyWithoutIdException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1421962491686528435L;

}
