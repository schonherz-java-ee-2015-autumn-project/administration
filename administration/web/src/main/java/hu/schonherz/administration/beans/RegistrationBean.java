package hu.schonherz.administration.beans;

import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import hu.schonherz.administration.serviceapi.UserService;
import hu.schonherz.administration.serviceapi.dto.UserDTO;

@ManagedBean
@ViewScoped
public class RegistrationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{UserService}")
	UserService userService;

	String name;
	String username;
	String phone;
	String password;
	String passconf;

	public void registration() {
		FacesContext current = FacesContext.getCurrentInstance();
		UserDTO user = new UserDTO();

		if (!passconf.equals(password)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "A két jelszónak meg kell egyeznie!", "A két jelszónak meg kell egyeznie!");
			current.addMessage(null, msg);
		} else {
			user.setName(name);
			user.setUsername(username);
			user.setPhoneNumber(phone);
			user.setPassword(password);
			try {
				userService.registrationUser(user);
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hiba a regisztráció közben!", "Hiba a regisztráció közben.");
				current.addMessage(null, msg);
				e.printStackTrace();
			}
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sikeres regisztráció!", "Sikeres regisztráció!");
			current.addMessage(null, msg);

		}
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

}
