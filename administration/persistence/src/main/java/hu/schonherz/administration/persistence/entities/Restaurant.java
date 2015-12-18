package hu.schonherz.administration.persistence.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Restaurant  extends BaseEntity implements Serializable{
	
	private static final long serialVersionUID = 7483949968557475123L;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;
	
	@Column(nullable = false)
	private String address;
	
	private Integer price;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	

}
