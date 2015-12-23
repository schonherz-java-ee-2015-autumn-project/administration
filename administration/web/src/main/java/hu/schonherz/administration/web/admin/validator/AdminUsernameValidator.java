package hu.schonherz.administration.web.admin.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import hu.schonherz.administration.web.localization.MessageProvider;

@FacesValidator("adminUsernameValidator")
public class AdminUsernameValidator implements Validator{
	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		String input=(String)arg2;
		String err = "";
		String usernameRegex="[0-9a-zöüóőúéáűíA-ZÖÜÓŐÚÉÁŰÍ/_]*";

		if (input.length() < 6)
			err += MessageProvider.getValue("usernameLengthError");

		if (input.length() > 16)
			err += MessageProvider.getValue("usernameLengthError");

		if(!input.matches(usernameRegex))
			err += MessageProvider.getValue("usernameRegexError");
		
		if (!err.isEmpty()) {
			FacesMessage message = new FacesMessage(err);
			message.setSeverity(FacesMessage.SEVERITY_WARN);
			throw new ValidatorException(message);
		}
	}
}
