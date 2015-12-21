package hu.schonherz.administration.web.restaurant;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import hu.schonherz.administration.serviceapi.RestaurantService;
import hu.schonherz.administration.serviceapi.dto.RestaurantDTO;
import hu.schonherz.administration.web.localization.MessageProvider;
import hu.schonherz.administration.web.restaurant.validator.RestaurantValidator;

@Named("restaurantEditBean")
@ViewScoped
@EJB(name = "ejb.RestaurantService", beanInterface = RestaurantService.class)
public class RestaurantEditBean {

	@EJB
	private RestaurantService restaurantService;
	private RestaurantDTO selected;

	public void modify() {
		if (RestaurantValidator.isValidRestaurant(selected)) {
			selected.setIsDeleted(false);
			try {
				restaurantService.save(selected);
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage message = new FacesMessage(MessageProvider.getValue("successful_save"));
				message.setSeverity(FacesMessage.SEVERITY_INFO);
				context.addMessage("restaurantForm:save_status", message);

			} catch (Exception e) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage("restaurantForm:save_status",
						new FacesMessage(MessageProvider.getValue("save_failed")));
			}
		}
	}

	public void onRowSelect(SelectEvent event) {
		if(!selected.equals(((RestaurantDTO)event.getObject()))){
			selected = (RestaurantDTO)event.getObject();
		}
	}

	public RestaurantService getRestaurantService() {
		return restaurantService;
	}

	public void setRestaurantService(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}

	public RestaurantDTO getSelected() {
		return selected;
	}

	public void setSelected(RestaurantDTO selected) {
		this.selected = selected;
	}
}
