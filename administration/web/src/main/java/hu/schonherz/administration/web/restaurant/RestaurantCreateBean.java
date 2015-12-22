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

@Named("RestaurantCreateBean")
@ViewScoped
@EJB(name = "ejb.RestaurantService", beanInterface = RestaurantService.class)
public class RestaurantCreateBean {

	@EJB
	private RestaurantService restaurantService;
	private String name;
	private String address;
	private String phoneNumber;
	private Integer price;
	private RestaurantDTO selected;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public void save() {
			RestaurantDTO restaurant = new RestaurantDTO();
			restaurant.setName(name);
			restaurant.setAddress(address);
			restaurant.setPhoneNumber(phoneNumber);
			restaurant.setPrice(price);
			restaurant.setIsDeleted(false);
			if (RestaurantValidator.isValidRestaurant(restaurant)) {
			try {
				restaurantService.save(restaurant);
				reset();
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



	public RestaurantService getRestaurantService() {
		return restaurantService;
	}

	public void setRestaurantService(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}

	private void reset() {
		this.name = null;
		this.address = null;
		this.phoneNumber = null;
		this.price = null;
	}

	public RestaurantDTO getSelected() {
		return selected;
	}

	public void setSelected(RestaurantDTO selected) {
		this.selected = selected;
	}

}
