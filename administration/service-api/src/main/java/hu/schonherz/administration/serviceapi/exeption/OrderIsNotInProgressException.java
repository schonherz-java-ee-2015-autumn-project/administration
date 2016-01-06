package hu.schonherz.administration.serviceapi.exeption;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class OrderIsNotInProgressException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6177591440291644044L;
	
	private String ErrorMessage;
	
	@Override
	public String getMessage(){
		return ErrorMessage;
	}
	
	public void setMessage(String message){
		ErrorMessage=message;
		
	}
	
}
