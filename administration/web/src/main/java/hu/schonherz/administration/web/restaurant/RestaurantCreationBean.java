package hu.schonherz.administration.web.restaurant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import hu.schonherz.administration.serviceapi.RestaurantService;
import hu.schonherz.administration.serviceapi.dto.RestaurantDTO;
import hu.schonherz.administration.web.localization.MessageProvider;

@Named("RestaurantCreationBean")
@ViewScoped
@EJB(name = "ejb.RestaurantService", beanInterface = RestaurantService.class)
public class RestaurantCreationBean {

	@EJB
	private RestaurantService restaurantService;

	private String name;
	private String address;
	private String phoneNumber;
	private Integer price;

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
		if (isValidAddress() && isValidName() && isValidPhoneNumber() && isValidPrice()) {
			RestaurantDTO restaurant = new RestaurantDTO();
			restaurant.setName(name);
			restaurant.setAddress(address);
			restaurant.setPhoneNumber(phoneNumber);
			restaurant.setPrice(price);
			restaurant.setIsDeleted(false);
			try {
				restaurantService.save(restaurant);
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

	private boolean isValidName() {
		if (name.length() < 3 && name.length() > 150)
			return false;

		if (!name.isEmpty()) {
			Pattern p = Pattern.compile("[\\w [0-9]-]+", Pattern.UNICODE_CHARACTER_CLASS);
			Matcher m = p.matcher(name);
			if (!m.matches())
				return false;
		}
		return true;
	}

	private boolean isValidAddress() {
		if (address.length() < 10 && address.length() > 500)
			return false;
		else
			return true;
	}

	private boolean isValidPhoneNumber() {
		if (phoneNumber.length() < 7 && phoneNumber.length() > 11)
			return false;
		else
			return true;
	}

	private boolean isValidPrice() {
		if (price > 0 && price < 99) {
			return true;
		} else {
			return false;
		}
	}

	public RestaurantService getRestaurantService() {
		return restaurantService;
	}

	public void setRestaurantService(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}
}
