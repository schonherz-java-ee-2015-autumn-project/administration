package hu.schonherz.administration.serviceapi.dto;

import java.io.Serializable;
import java.util.List;

public class RestaurantDTO implements Serializable{

	private static final long serialVersionUID = -8625606961519943129L;
	
	private Long id;
	private String name;
	private String address;
	private String phoneNumber;
	private Integer price;
	private Boolean isDeleted;
	private List<UserDTO> employees;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RestaurantDTO other = (RestaurantDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	public List<UserDTO> getEmployees() {
		return employees;
	}
	public void setEmployees(List<UserDTO> employees) {
		this.employees = employees;
	}
	
	
	

}
