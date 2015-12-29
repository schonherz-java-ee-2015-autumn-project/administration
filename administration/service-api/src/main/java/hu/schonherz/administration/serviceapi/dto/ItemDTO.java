package hu.schonherz.administration.serviceapi.dto;

import java.io.Serializable;

public class ItemDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8537394142811478003L;

	private String name;
	private int price;
	private int count;
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
