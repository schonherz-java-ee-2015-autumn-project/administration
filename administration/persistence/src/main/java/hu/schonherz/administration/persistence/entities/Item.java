package hu.schonherz.administration.persistence.entities;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Item extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1480123363657426029L;
	
	
	private String name;
	private Integer price;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}

}
