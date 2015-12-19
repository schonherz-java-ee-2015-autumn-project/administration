package hu.schonherz.administration.web.restaurant.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import hu.schonherz.administration.web.localization.MessageProvider;

@FacesValidator("priceValidator")
public class RestaurantPriceValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		try {
			Integer input = (Integer) value;
			if (input != null && input > 0) {

			} else {
				FacesMessage message = new FacesMessage(MessageProvider.getValue("restaurant_price"));
				message.setSeverity(FacesMessage.SEVERITY_WARN);
				throw new ValidatorException(message);
			}
		} catch (ClassCastException e) {
			FacesMessage message = new FacesMessage(MessageProvider.getValue("invalid_input"));
			message.setSeverity(FacesMessage.SEVERITY_WARN);
			throw new ValidatorException(message);
		}
	}

}