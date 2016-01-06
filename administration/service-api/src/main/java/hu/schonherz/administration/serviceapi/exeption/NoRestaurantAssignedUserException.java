package hu.schonherz.administration.serviceapi.exeption;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class NoRestaurantAssignedUserException extends Exception {

	private static final long serialVersionUID = -5973380655795963481L;

	private String errMessage;

	public NoRestaurantAssignedUserException(String string) {
		setErrMessage(string);
	}
	
	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}
}
