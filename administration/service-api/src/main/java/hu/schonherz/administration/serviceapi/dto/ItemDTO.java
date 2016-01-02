package hu.schonherz.administration.serviceapi.dto;

import java.io.Serializable;

public class ItemDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8537394142811478003L;

	private Long id;
	private String name;
	private int price;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}
