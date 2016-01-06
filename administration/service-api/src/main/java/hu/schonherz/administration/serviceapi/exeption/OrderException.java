package hu.schonherz.administration.serviceapi.exeption;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class OrderException extends Exception{

	private static final long serialVersionUID = 4360510489661501386L;
	
	private String errMessage;

	public OrderException(String string) {
		setErrMessage(string);
	}

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

}
