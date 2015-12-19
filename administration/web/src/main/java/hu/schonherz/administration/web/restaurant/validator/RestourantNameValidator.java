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

@FacesValidator("nameValidator")
public class RestourantNameValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    		String input = (String) value;
    		String err = "";
    		if(input.length()<3)
    			err += MessageProvider.getValue("restaurant_name_min_length");
    		if(input.length()>150)
    			err += MessageProvider.getValue("restaurant_name_max_length");
    		
    		 Pattern p = Pattern.compile("[\\w [0-9]-]+",Pattern.UNICODE_CHARACTER_CLASS);
    		 Matcher m = p.matcher(input);
    		 if(!m.matches())
    			 err += MessageProvider.getValue("restaurant_name_constraint");
   
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