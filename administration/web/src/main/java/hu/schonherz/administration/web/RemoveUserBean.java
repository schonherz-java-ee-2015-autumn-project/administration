package hu.schonherz.administration.web;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

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
		
		try {
			userService.removeUser(id);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		
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
