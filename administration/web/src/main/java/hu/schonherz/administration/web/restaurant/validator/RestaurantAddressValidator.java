package hu.schonherz.administration.web.restaurant.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import hu.schonherz.administration.web.localization.MessageProvider;

@FacesValidator("addressValidator")
public class RestaurantAddressValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String input = (String) value;
		String err = "";
		try {
			if (input.length() < 10)
				err += MessageProvider.getValue("restaurant_address_min_length");
		} catch (Exception e) {
			err += e.toString();
		}
		if (input.length() > 500)
			err += MessageProvider.getValue("restaurant_address_max_length");

		if (!err.isEmpty()) {
			FacesMessage message = new FacesMessage(err);
			message.setSeverity(FacesMessage.SEVERITY_WARN);
			throw new ValidatorException(message);
		} else {
			FacesMessage message = new FacesMessage(MessageProvider.getValue("valid_input"));
			message.setSeverity(FacesMessage.SEVERITY_INFO);
			throw new ValidatorException(message);
		}
	}

}