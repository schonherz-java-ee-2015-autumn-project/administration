package hu.schonherz.administration.web.courier;

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

@Named("courierEditBean")
@ViewScoped
@EJB(name = "ejb.UserService", beanInterface = UserService.class)
public class CourierModifyBean {

	@EJB
	private UserService userService;
	private UserDTO selected;
	private long id;
	 
	BCryptPasswordEncoder BCrypt = new BCryptPasswordEncoder();
	public void modify() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (UserValidator.isValidEditUser(selected)) {
			try {
				userService.saveUser(selected);
				
				context.addMessage("courierForm:save_courier_status", new FacesMessage(MessageProvider.getValue("successful_edit")));

			} catch (Exception e) {
				context.addMessage("courierForm:save_courier_status",
						new FacesMessage(MessageProvider.getValue("edit_failed")));
			}
		}else{
			context.addMessage("courierForm:save_courier_status",
					new FacesMessage(MessageProvider.getValue("edit_failed")));
		}
	}
	
	public void init() {
		selected = userService.findById(id);
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

	
	
}
