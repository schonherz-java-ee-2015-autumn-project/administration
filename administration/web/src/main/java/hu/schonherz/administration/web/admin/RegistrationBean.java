package hu.schonherz.administration.web.admin;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import hu.schonherz.administration.serviceapi.UserService;
import hu.schonherz.administration.serviceapi.dto.UserDTO;
import hu.schonherz.administration.web.localization.MessageProvider;
import hu.schonherz.administration.web.validator.UserValidator;

@ViewScoped
@Named("registrationBean")
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
		user.setPassword(password);
		user.setRemove(false);
		if (UserValidator.isValidUser(user) && password.equals(passconf)) {
			try {
				user.setPassword(bCryptPasswordEncoder.encode(password));
				userService.registrationAdmin(user);
			} catch (Exception e) {

				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", MessageProvider.getValue("regfailure"));
				current.addMessage(null, msg);
				e.printStackTrace();
			}
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "", MessageProvider.getValue("regsucces"));
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
