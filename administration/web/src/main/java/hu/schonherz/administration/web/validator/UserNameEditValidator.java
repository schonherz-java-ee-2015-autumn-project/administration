package hu.schonherz.administration.web.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import hu.schonherz.administration.web.localization.MessageProvider;

@FacesValidator("userNameEditValidator")
public class UserNameEditValidator implements Validator {

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		
		
		String input=(String)arg2;
		String err = "";
		if (input.length() < 6)
			err += MessageProvider.getValue("usernameLengthError");
		if (input.length() > 16)
			err += MessageProvider.getValue("usernameLengthError");
		if (!input.isEmpty()) {
			Pattern p = Pattern.compile("[\\w[0-9]_]+", Pattern.UNICODE_CHARACTER_CLASS);
			Matcher m = p.matcher(input);
			if (!m.matches())
				err += MessageProvider.getValue("username_constraint");
		}
		if (!err.isEmpty()) {
			FacesMessage message = new FacesMessage(err);
			message.setSeverity(FacesMessage.SEVERITY_WARN);
			throw new ValidatorException(message);
		}
	}
}