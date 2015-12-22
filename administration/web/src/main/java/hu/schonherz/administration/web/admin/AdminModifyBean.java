package hu.schonherz.administration.web.admin;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import hu.schonherz.administration.serviceapi.UserService;
import hu.schonherz.administration.serviceapi.dto.UserDTO;
import hu.schonherz.administration.web.localization.MessageProvider;

@Named("adminEditBean")
@ViewScoped
@EJB(name = "ejb.UserService", beanInterface = UserService.class)
public class AdminModifyBean {
	
	@EJB
	private UserService userService;
	private UserDTO selected;
	private long id;
	
	public void modify() {
			try {
				userService.saveUser(selected);
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage message = new FacesMessage(MessageProvider.getValue("successful_edit"));
				message.setSeverity(FacesMessage.SEVERITY_INFO);
				context.addMessage("adminForm:save_status", message);

			} catch (Exception e) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage("adminForm:save_status",
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
