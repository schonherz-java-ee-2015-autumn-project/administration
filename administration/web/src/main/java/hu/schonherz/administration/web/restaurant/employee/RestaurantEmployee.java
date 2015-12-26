package hu.schonherz.administration.web.restaurant.employee;

import java.io.Serializable;
import java.util.List;

import hu.schonherz.administration.serviceapi.dto.RestaurantDTO;
import hu.schonherz.administration.serviceapi.dto.RoleDTO;

public class RestaurantEmployee implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4348556556310512910L;
	private Long id;
	private String phoneNumber;
	private String name;
	private String username;
	private String password;
	private List<RoleDTO> roles;
	private boolean remove;
	private RestaurantDTO restaurant;
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<RoleDTO> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
	}
	public boolean isRemove() {
		return remove;
	}
	public void setRemove(boolean remove) {
		this.remove = remove;
	}
	public RestaurantDTO getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(RestaurantDTO restaurant) {
		this.restaurant = restaurant;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
