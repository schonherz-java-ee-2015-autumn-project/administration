package hu.schonherz.administration.web.admin.validator;

import javax.faces.validator.FacesValidator;
import hu.schonherz.administration.web.restaurant.validator.RestourantNameValidator;

@FacesValidator("adminNameValidator")
public class AdminNameValidator extends RestourantNameValidator{

}