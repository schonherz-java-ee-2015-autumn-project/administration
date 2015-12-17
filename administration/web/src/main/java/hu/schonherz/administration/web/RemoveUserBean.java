package hu.schonherz.administration.web;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import hu.schonherz.administration.serviceapi.UserService;

@ManagedBean(name = "UserBean")
public class RemoveUserBean implements Serializable {

	private static final long serialVersionUID = 4552990854623398173L;

	Logger  logger = Logger.getLogger(RemoveUserBean.class);
	
	@EJB
	private UserService userService;
	
	@ManagedProperty(value = "#{param.userID}")
	private long id;
	
	public void removeUser() {

		String msg = new MessageProvider().getValue("remove");
		String msg1 = new MessageProvider().getValue("remove_accept");
		addMessage(msg, msg1);
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
