package hu.schonherz.administration.web.restaurant.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("nameValidator")
public class RestourantNameValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    		String input = (String) value;
    		String err = "";
    		if(input.length()<3)
    			err += "Name must be at least 3 characters long.";
    		if(input.length()>150)
    			err += "Name must be less than 150 characters long.";
    		
    		 Pattern p = Pattern.compile("[\\w [0-9]-]+",Pattern.UNICODE_CHARACTER_CLASS);
    		 Matcher m = p.matcher(input);
    		 if(!m.matches())
    			 err += "Name can onlye contain letters number spaces and '-'.";
   
        if (!err.isEmpty()) {
            throw new ValidatorException(new FacesMessage(err));
        }
    }

}