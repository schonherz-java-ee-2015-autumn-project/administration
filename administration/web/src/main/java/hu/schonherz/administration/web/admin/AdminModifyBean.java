package hu.schonherz.administration.web.admin;

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

@Named("adminEditBean")
@ViewScoped
@EJB(name = "ejb.UserService", beanInterface = UserService.class)
public class AdminModifyBean {
	
	@EJB
	private UserService userService;
	private UserDTO selected;
	private long id;
	private String password = ""; 
	BCryptPasswordEncoder BCrypt = new BCryptPasswordEncoder();
	
	public void modify() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (UserValidator.isValidEditUser(selected)) {
			try {
				if(!password.isEmpty()){
					selected.setPassword(password);
					if(UserValidator.isValidUser(selected)){
						selected.setPassword(BCrypt.encode(password));
					}else{
						context.addMessage("AdminForm:save_status",
								new FacesMessage(MessageProvider.getValue("edit_failed")));
					}
				}
				userService.saveUser(selected);
				password = "";
				context.addMessage("adminForm:save_status", new FacesMessage(MessageProvider.getValue("successful_edit")));

			} catch (Exception e) {
				context.addMessage("adminForm:save_status",
						new FacesMessage(MessageProvider.getValue("edit_failed")));
			}
		}else{
			context.addMessage("AdminForm:save_status",
					new FacesMessage(MessageProvider.getValue("edit_failed")));
		}
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
