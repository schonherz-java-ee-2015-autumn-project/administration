package hu.schonherz.administration.web.restaurant.employee;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import hu.schonherz.administration.serviceapi.RestaurantService;
import hu.schonherz.administration.serviceapi.UserService;
import hu.schonherz.administration.serviceapi.dto.RestaurantDTO;
import hu.schonherz.administration.serviceapi.dto.UserDTO;
import hu.schonherz.administration.web.localization.MessageProvider;
import hu.schonherz.administration.web.validator.UserValidator;

@ViewScoped
@Named("restaurantUserRegistrationBean")

public class RestaurantUserRegistrationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB(lookup = "ejb.UserService", beanInterface = UserService.class)
	UserService userService;

	@EJB(lookup = "ejb.RestaurantService", beanInterface = RestaurantService.class)
	RestaurantService restaurantService;

	String name;
	String username;
	String phone;
	String password;
	String passconf;
	RestaurantDTO selectedRestaurant;
	List<RestaurantDTO> restaurants;
	FacesMessage msg;
	FacesContext current;

	public void registration() {

		current = FacesContext.getCurrentInstance();
		UserDTO user = new UserDTO();
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

		user.setName(name);
		user.setUsername(username);
		user.setPhoneNumber(phone);
		user.setPassword(password);
		user.setRemove(false);
		if (UserValidator.isValidUser(user) && password.equals(passconf)) {

			user.setPassword(bCryptPasswordEncoder.encode(password));
			UserDTO savedUser;
			try {
				savedUser = userService.saveRestaurantUser(user);
				if(selectedRestaurant!=null){
				selectedRestaurant.getEmployees().add(savedUser);
				restaurantService.save(selectedRestaurant);
				}
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "",
						MessageProvider.getValue("successful_save"));
				current.addMessage("userregform:save_status", msg);
				name = username = phone = null;
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "",
						MessageProvider.getValue("save_failed"));
				current.addMessage("userregform:save_status", msg);
			}
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "",
					MessageProvider.getValue("save_failed"));
			current.addMessage("userregform:save_status", msg);
		}
	}

	public void init() {
		restaurants = restaurantService.getOnlyActiveRestaurants();
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassconf() {
		return passconf;
	}

	public void setPassconf(String passconf) {
		this.passconf = passconf;
	}

	public RestaurantService getRestaurantService() {
		return restaurantService;
	}

	public void setRestaurantService(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
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

}
