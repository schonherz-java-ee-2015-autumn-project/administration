package hu.schonherz.administration.web.restaurant.validator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("priceValidator")
public class RestaurantPriceValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    		
//    		String input = (String) value;
//    		String err = "";
//    		if(input.length()<1)
//    			err += "Price must be at least 1 characters long.";
//    		if(input.length()>2)
//    			err += "Price cannot be more than 99%";
//    		
//    		 Pattern p = Pattern.compile("[0-9]+",Pattern.UNICODE_CHARACTER_CLASS);
//    		 Matcher m = p.matcher(input);
//    		 if(!m.matches())
//    			 err += "Name can only contain letters number spaces and '-'.";
//   
//        if (!err.isEmpty()) {
//            throw new ValidatorException(new FacesMessage(err));
//        }
    		Integer input = (Integer) value;
    }

}