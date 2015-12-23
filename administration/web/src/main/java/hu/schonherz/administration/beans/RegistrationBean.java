package hu.schonherz.administration.beans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import hu.schonherz.administration.serviceapi.UserService;
import hu.schonherz.administration.serviceapi.dto.UserDTO;
import hu.schonherz.administration.web.admin.validator.AdminValidator;

@RequestScoped
@ManagedBean
@EJB(name = "ejb.UserService", beanInterface = UserService.class)
public class RegistrationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	UserService userService;

	String name;
	String username;
	String phone;
	String password;
	String passconf;
	FacesMessage msg;
	FacesContext current;

	public void registration() {
		
		//UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
		current = FacesContext.getCurrentInstance();
		UserDTO user = new UserDTO();
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

		user.setName(name);
		user.setUsername(username);
		user.setPhoneNumber(phone);
		user.setPassword(bCryptPasswordEncoder.encode(password));
		user.setRemove(false);
		if (AdminValidator.isValidAdmin(user) && password.equals(passconf)) {
			try {
				userService.registrationAdmin(user);
			} catch (Exception e) {

				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hiba a regisztráció közben!", "Hiba a regisztráció közben.");
				current.addMessage(null, msg);
				e.printStackTrace();
			}
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sikeres regisztráció!", "Sikeres regisztráció!");
			current.addMessage(null, msg);
			name = username = phone = null;
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
