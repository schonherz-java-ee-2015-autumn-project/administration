package hu.schonherz.administration.web.admin;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import hu.schonherz.administration.serviceapi.UserService;
import hu.schonherz.administration.web.localization.MessageProvider;

@ManagedBean(name = "UserBean")
public class AdminRemoveBean implements Serializable {

	private static final long serialVersionUID = 4552990854623398173L;

	Logger  logger = Logger.getLogger(AdminRemoveBean.class);
	
	@EJB
	private UserService userService;
	
	@ManagedProperty(value = "#{param.userID}")
	private long id;
	
	public void removeUser() {

		MessageProvider.getValue("remove_accept");
		addMessage(MessageProvider.getValue("remove"), MessageProvider.getValue("remove_accept"));
		try {
			userService.removeUser(id);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		
	}
	public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	
}
