package hu.schonherz.administration.web.restaurant;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

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
	private long id;

	public void modify() {
		if (RestaurantValidator.isValidRestaurant(selected) && !selected.getIsDeleted()) {
			try {
				restaurantService.save(selected);
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage message = new FacesMessage(MessageProvider.getValue("successful_edit"));
				message.setSeverity(FacesMessage.SEVERITY_INFO);
				context.addMessage("restaurantForm:save_status", message);

			} catch (Exception e) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage("restaurantForm:save_status",
						new FacesMessage(MessageProvider.getValue("edit_failed")));
			}
		}else{
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("restaurantForm:save_status",
					new FacesMessage(MessageProvider.getValue("edit_failed")));
		}
	}
	
	public void delete() {
		if (RestaurantValidator.isValidRestaurant(selected) && !selected.getIsDeleted()) {
			selected.setIsDeleted(true);
			try {
				restaurantService.save(selected);
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage message = new FacesMessage(MessageProvider.getValue("successful_deletion"));
				message.setSeverity(FacesMessage.SEVERITY_INFO);
				context.addMessage("restaurantForm:save_status", message);

			} catch (Exception e) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage("restaurantForm:save_status",
						new FacesMessage(MessageProvider.getValue("deletion_failed")));
			}
		}
	}

	public void init() {
		selected = restaurantService.findById(id);

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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
