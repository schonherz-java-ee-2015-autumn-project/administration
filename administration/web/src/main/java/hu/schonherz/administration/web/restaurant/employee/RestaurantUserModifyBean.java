package hu.schonherz.administration.web.restaurant.employee;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import hu.schonherz.administration.serviceapi.RestaurantService;
import hu.schonherz.administration.serviceapi.UserService;
import hu.schonherz.administration.serviceapi.dto.RestaurantDTO;
import hu.schonherz.administration.serviceapi.dto.UserDTO;
import hu.schonherz.administration.web.localization.MessageProvider;
import hu.schonherz.administration.web.validator.UserValidator;

@Named("restaurantUserEditBean")
@ViewScoped
public class RestaurantUserModifyBean {

	@EJB(lookup = "ejb.UserService", beanInterface = UserService.class)
	private UserService userService;

	@EJB(lookup = "ejb.RestaurantService", beanInterface = RestaurantService.class)
	private RestaurantService restaurantService;
	private UserDTO selected;
	private long id;
	private RestaurantDTO selectedRestaurant;
	private List<RestaurantDTO> restaurants;
	private RestaurantDTO oldRestaurant;

	public void modify() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (UserValidator.isValidEditUser(selected)) {
			try {
				userService.saveUser(selected);
				if (oldRestaurant == null && selectedRestaurant!=null) {
					selectedRestaurant.getEmployees().add(selected);
					restaurantService.save(selectedRestaurant);
				} else if (!selectedRestaurant.equals(oldRestaurant)) {
						oldRestaurant.getEmployees().remove(selected);
						selectedRestaurant.getEmployees().add(selected);
						restaurantService.save(oldRestaurant);
						restaurantService.save(selectedRestaurant);
				}
				context.addMessage("adminForm:edit_status",
						new FacesMessage(MessageProvider.getValue("successful_edit")));

			} catch (Exception e) {
				context.addMessage("adminForm:edit_status", new FacesMessage(MessageProvider.getValue("edit_failed")));
			}
		} else {
			context.addMessage("adminForm:edit_status", new FacesMessage(MessageProvider.getValue("edit_failed")));
		}
	}

	public void delete() {

		try {

			userService.removeUser(selected.getId());
			FacesMessage m = new FacesMessage(MessageProvider.getValue("successful_deletion"));
			addMessage(m);

		} catch (Exception e) {
			FacesMessage m = new FacesMessage(MessageProvider.getValue("deletion_failed"));
			addMessage(m);
		}

	}

	private void addMessage(FacesMessage message) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("adminForm:deletion_status", message);
	}

	public void init() {
		selected = userService.findById(id);
		restaurants = restaurantService.getOnlyActiveRestaurants();
		oldRestaurant = restaurantService.findRestaurantByUser(selected);
		if (oldRestaurant != null)
			selectedRestaurant = oldRestaurant;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserDTO getSelected() {
		return selected;
	}

	public void setSelected(UserDTO selected) {
		this.selected = selected;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public RestaurantDTO getSelectedRestaurant() {
		return selectedRestaurant;
	}

	public void setSelectedRestaurant(RestaurantDTO selectedRestaurant) {
		this.selectedRestaurant = selectedRestaurant;
	}

	public List<RestaurantDTO> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(List<RestaurantDTO> restaurants) {
		this.restaurants = restaurants;
	}

	public RestaurantService getRestaurantService() {
		return restaurantService;
	}

	public void setRestaurantService(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}

	public RestaurantDTO getOldRestaurant() {
		return oldRestaurant;
	}

	public void setOldRestaurant(RestaurantDTO oldRestaurant) {
		this.oldRestaurant = oldRestaurant;
	}

}
