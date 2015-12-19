package hu.schonherz.administration.web.admin;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;

import hu.schonherz.administration.serviceapi.UserService;

@ManagedBean
@Named("AdminModifyBean")
public class AdminModifyBean {
	
	@EJB
	private UserService userService;
	
	
	@ManagedProperty(value = "#{param.userID}")
	private long id;
	
	
	
	
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
	
	private String name;
	private String userName;
	private String phoneNumber;
	
	
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
