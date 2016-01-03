package hu.schonherz.administration.serviceapi.exeption;

public class AddressNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8427877022690745676L;
	
	private String ErrorMessage;
	
	@Override
	public String getMessage(){
		return ErrorMessage;
	}
	
	public void setMessage(String message){
		ErrorMessage=message;
		
	}

}
