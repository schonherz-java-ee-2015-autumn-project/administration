package hu.schonherz.administration.serviceapi.exeption;

import javax.ejb.ApplicationException;

/**
 * 
 * @author Miklós Kosárkár
 * This exceptions is thrown when the required parameter has invalid fields according to documentation.
 *
 */
@ApplicationException(rollback=true)
public class InvalidFieldValuesException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7282489612757656918L;
	
	private String errMessage;
	
	@Override
	public String getMessage() {
		return  errMessage;
	}
	
	public void setMessage(String message){
		this.errMessage=message;
	}
}
