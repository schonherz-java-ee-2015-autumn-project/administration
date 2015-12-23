package hu.schonherz.administration.web.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import hu.schonherz.administration.web.localization.MessageProvider;
@FacesValidator("PasswordValidator")
public class PasswordValidator implements Validator{

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		String input=(String)arg2;
		String err = "";
		String passwordNumberRegex=".*[0-9]+.*";
		String passwordUppercaseRegex=".*[A-Z]+.*";
		String passwordLowercaseRegex=".*[a-z]+.*";
		
		if (input.length() < 8)
			err += MessageProvider.getValue("passwordLengthError");

		if (input.length() > 12)
			err += MessageProvider.getValue("passwordLengthError");

		if(!input.matches(passwordNumberRegex) || !input.matches(passwordUppercaseRegex) || !input.matches(passwordLowercaseRegex))
			err += MessageProvider.getValue("passwordRegexError");
		
		if (!err.isEmpty()) {
			FacesMessage message = new FacesMessage(err);
			message.setSeverity(FacesMessage.SEVERITY_WARN);
			throw new ValidatorException(message);
		
	}
}

}
