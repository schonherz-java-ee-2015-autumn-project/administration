package hu.schonherz.administration.persistence.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ItemQuantity extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9080123029284843504L;
	
	@ManyToOne
	private Item item;
	private Integer quantity;
	
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
}
