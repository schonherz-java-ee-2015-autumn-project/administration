package hu.schonherz.administration.serviceapi.exeption;

public class NotAllowedRoleException extends Exception {

	private static final long serialVersionUID = -7247043701282868348L;

	private String errMessage;

	public NotAllowedRoleException(String string) {
		setErrMessage(string);
	}

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

}
