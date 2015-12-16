package hu.schonherz.administration.web;

import java.security.Principal;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import hu.schonherz.administration.serviceapi.UserService;
import hu.schonherz.administration.serviceapi.dto.UserDTO;


@SessionScoped
@ManagedBean(name = "userSessionBean")
public class UserSessionBean {
	Logger  logger = Logger.getLogger(UserSessionBean.class);
	@EJB
	private UserService userService;

	private UserDTO userDTO;

	public void init() {
			HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
					.getRequest();
			if (userDTO == null) {
				Principal principal = req.getUserPrincipal();
				if (principal != null) {
					String userName = principal.getName();
					try {
						userDTO = getUserService().findUserByName(userName);
					} catch (UsernameNotFoundException e) {
						logger.info(e.getMessage());
					} catch (Exception e) {
						logger.info(e.getMessage());
					}
				}
			}
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setUserVO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public UserDTO getUserDTO() {
		init();
		return userDTO;
	}
}
