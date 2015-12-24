package hu.schonherz.administration.web.restaurant;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Named;

import hu.schonherz.administration.serviceapi.RestaurantService;
import hu.schonherz.administration.serviceapi.dto.RestaurantDTO;

@Named("restaurantConverter")
@ViewScoped
@EJB(name = "ejb.RestaurantService", beanInterface = RestaurantService.class)
public class RestaurantConverter implements Converter {
	
	@EJB
	RestaurantService restaurantService;
	
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
               
                return restaurantService.findById(Long.parseLong(value));
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            }
        }
        else {
            return null;
        }
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((RestaurantDTO) object).getId());
        }
        else {
            return null;
        }
    }   
}   