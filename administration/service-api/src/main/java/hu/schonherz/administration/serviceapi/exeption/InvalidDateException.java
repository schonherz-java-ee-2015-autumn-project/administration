package hu.schonherz.administration.serviceapi.exeption;

public class InvalidDateException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4405312700143125890L;
	
	private String ErrorMessage;
	
	@Override
	public String getMessage(){
		return ErrorMessage;
	}
	
	public void setMessage(String message){
		ErrorMessage=message;
		
	}

}
