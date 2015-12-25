package hu.schonherz.administration.web.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

import hu.schonherz.administration.serviceapi.UserService;
import hu.schonherz.administration.serviceapi.dto.UserDTO;
import hu.schonherz.administration.web.localization.MessageProvider;

@Named("userNameEditValidator")
@EJB(name = "ejb.UserService", beanInterface = UserService.class)
@ViewScoped
public class UserNameEditValidator implements Validator {

	@EJB
	UserService userService;

	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		String input = (String) value;
		UIComponent otherpass = component.findComponent("adminForm:selectId");
		Long id = (Long) otherpass.getAttributes().get("value");
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
		if(err.isEmpty()){
			
			try {
				UserDTO user = userService.findUserByName(input);
				if(user!=null && !(user.getId().equals(id))){
					err+=MessageProvider.getValue("takenNameError");
				}
			} catch (Exception e) {
			}
		}
		if (!err.isEmpty()) {
			FacesMessage message = new FacesMessage(err);
			message.setSeverity(FacesMessage.SEVERITY_WARN);
			throw new ValidatorException(message);
		}
	}

}