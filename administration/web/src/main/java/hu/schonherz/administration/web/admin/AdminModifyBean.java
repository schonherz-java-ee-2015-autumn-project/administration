package hu.schonherz.administration.web.admin;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import hu.schonherz.administration.serviceapi.UserService;

@ManagedBean(name = "AdminModifyBean")
public class AdminModifyBean {
	
	@EJB
	private UserService userService;
	
	
	@ManagedProperty(value = "#{param.userID}")
	private long id;
	
	@ManagedProperty(value = "#{param.name}")
	private String name;
	
	@ManagedProperty(value = "#{param.userName}")
	private String userName;
	
	@ManagedProperty(value = "#{param.phoneNumber}")
	private String phoneNumber;
	
	
	
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public void setUserData(){
		
		try {
			
			userService.setUserData(id);
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	
	
	
}
