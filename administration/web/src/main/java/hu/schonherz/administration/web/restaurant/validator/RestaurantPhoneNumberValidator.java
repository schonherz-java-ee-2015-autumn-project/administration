package hu.schonherz.administration.web.restaurant.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import hu.schonherz.administration.web.localization.MessageProvider;

@FacesValidator("phoneNumberValidator")
public class RestaurantPhoneNumberValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String input = (String) value;
		String err = "";
		input = input.replace(" ", "");
		if (input.length() < 7)
			err += MessageProvider.getValue("restaurant_phone_number_min_length");
		if (input.length() > 11)
			err += "Phone number cannot contain more than 11 digist.";

		if (!input.isEmpty()) {
			Pattern p = Pattern.compile("[0-9]+", Pattern.UNICODE_CHARACTER_CLASS);
			Matcher m = p.matcher(input);
			if (!m.matches())
				err += MessageProvider.getValue("restaurant_phone_number_only_digits");
		}
		if (!err.isEmpty()) {
			FacesMessage message = new FacesMessage(err);
			message.setSeverity(FacesMessage.SEVERITY_WARN);
			throw new ValidatorException(message);
		}
	}

}