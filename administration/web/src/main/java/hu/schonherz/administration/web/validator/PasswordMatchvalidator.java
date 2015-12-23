package hu.schonherz.administration.web.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import hu.schonherz.administration.web.localization.MessageProvider;

@FacesValidator("passMatchValidator")
public class PasswordMatchvalidator implements Validator{

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		String input=(String)arg2;
		UIComponent otherpass = arg1.findComponent("userregform:password");
		String otherPassword = (String) otherpass.getAttributes().get("value");
		String err = "";

		if(!input.equals(otherPassword))
			err+=MessageProvider.getValue("passwords_not_match");
		if (!err.isEmpty())  {
			FacesMessage message = new FacesMessage(err);
			message.setSeverity(FacesMessage.SEVERITY_WARN);
			throw new ValidatorException(message);
		}
	}
	
}
