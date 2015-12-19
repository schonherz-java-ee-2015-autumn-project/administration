package hu.schonherz.administration.web.restaurant.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("phoneNumberValidator")
public class RestaurantPhoneNumberValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    		String input = (String) value;
    		String err = "";
    		if(input.length()<7)
    			err += "Phone number must be at least 7 digits long.";
    		if(input.length()>11)
    			err += "Phone number cannot contain more than 11 digist.";
    		
    		 Pattern p = Pattern.compile("[0-9]+",Pattern.UNICODE_CHARACTER_CLASS);
    		 Matcher m = p.matcher(input);
    		 if(!m.matches())
    			 err += "Phone number can only contain digits.";
   
        if (!err.isEmpty()) {
            throw new ValidatorException(new FacesMessage(err));
        }
    }

}