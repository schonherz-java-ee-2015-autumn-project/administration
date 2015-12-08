package hu.schonherz.administration.web;

import java.security.Principal;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import hu.schonherz.administration.serviceapi.UserService;
import hu.schonherz.administration.serviceapi.dto.UserDTO;


@SessionScoped
@ManagedBean(name = "userSessionBean")
public class UserSessionBean {

	@EJB
	private UserService userService;

	private UserDTO userDTO;

	@PostConstruct
	public void init() {
		try {
			HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
					.getRequest();
			if (userDTO == null) {
				userDTO = null;
				Principal principal = req.getUserPrincipal();
				if (principal != null) {
					String userName = principal.getName();
					try {
						userDTO = getUserService().findUserByName(userName);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
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
