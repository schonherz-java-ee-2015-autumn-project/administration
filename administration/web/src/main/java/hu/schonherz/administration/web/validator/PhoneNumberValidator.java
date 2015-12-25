package hu.schonherz.administration.web.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import hu.schonherz.administration.web.localization.MessageProvider;

@FacesValidator("PhoneNumberValidator")
public class PhoneNumberValidator implements Validator {
	
	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		String input=(String)arg2;
		String err = "";
		String phoneRegex="[0-9]*";
		
		if (input.length() < 7)
			err += MessageProvider.getValue("phoneLengthError");

		if (input.length() > 11)
			err += MessageProvider.getValue("phoneLengthError");

		if(!input.matches(phoneRegex))
			err += MessageProvider.getValue("phoneRegexError");
		
		if (!err.isEmpty()) {
			FacesMessage message = new FacesMessage(err);
			message.setSeverity(FacesMessage.SEVERITY_WARN);
			throw new ValidatorException(message);
		
	}
}

}