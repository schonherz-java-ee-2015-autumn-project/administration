package hu.schonherz.administration.serviceapi.exeption;

public class WrongCourierException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5482075226674041871L;
	
	private String ErrorMessage;
	
	@Override
	public String getMessage(){
		return ErrorMessage;
	}
	
	public void setMessage(String message){
		ErrorMessage=message;
		
	}
	
	
}
