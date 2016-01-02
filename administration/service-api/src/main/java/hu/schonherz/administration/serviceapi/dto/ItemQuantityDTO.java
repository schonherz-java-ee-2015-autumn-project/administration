package hu.schonherz.administration.serviceapi.dto;

import java.io.Serializable;

public class ItemQuantityDTO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8511897837377933747L;

	private Long id;
	private ItemDTO item;
	private Integer quantity;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ItemDTO getItem() {
		return item;
	}
	public void setItem(ItemDTO item) {
		this.item = item;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
}
