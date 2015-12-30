package hu.schonherz.administration.wsservice.dto;

import java.io.Serializable;

public class RemoteItemQuantityDTO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4318317100283187023L;
	
	private Long id;
	private RemoteItemDTO itemDTO;
	private Integer quantity;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public RemoteItemDTO getItemDTO() {
		return itemDTO;
	}
	public void setItemDTO(RemoteItemDTO itemDTO) {
		this.itemDTO = itemDTO;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
	
}
