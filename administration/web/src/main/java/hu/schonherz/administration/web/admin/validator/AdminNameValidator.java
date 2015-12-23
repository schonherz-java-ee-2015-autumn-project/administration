package hu.schonherz.administration.web.admin.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import hu.schonherz.administration.web.localization.MessageProvider;


@FacesValidator("adminNameValidator")
public class AdminNameValidator implements Validator{

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		String input=(String)arg2;
		String err = "";
		String nameRegex="[' 'a-zöüóőúéáűíA-ZÖÜÓŐÚÉÁŰÍ/-]*";

		if (input.length() < 3)
			err += MessageProvider.getValue("nameLengthError");

		if (input.length() > 150)
			err += MessageProvider.getValue("nameLengthError");

		if(!input.matches(nameRegex))
			err += MessageProvider.getValue("nameRegexError");
		
		if (!err.isEmpty()) {
			FacesMessage message = new FacesMessage(err);
			message.setSeverity(FacesMessage.SEVERITY_WARN);
			throw new ValidatorException(message);
		}
	}
		
}
