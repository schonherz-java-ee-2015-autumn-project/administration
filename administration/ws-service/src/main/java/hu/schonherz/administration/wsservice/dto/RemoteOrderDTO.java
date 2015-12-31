package hu.schonherz.administration.wsservice.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class RemoteOrderDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7688276666937378335L;
	
	private Long id;
	private String addressToDeliver;
	private Date deadline;
	private RemotePaymentMethod payment;
	private int fullCost;
	private DeliveryStateWeb deliveryState;
	private List<RemoteItemQuantityDTO> items;
	public String getAddressToDeliver() {
		return addressToDeliver;
	}
	public void setAddressToDeliver(String addressToDeliver) {
		this.addressToDeliver = addressToDeliver;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public RemotePaymentMethod getPayment() {
		return payment;
	}
	public void setPayment(RemotePaymentMethod payment) {
		this.payment = payment;
	}
	public int getFullCost() {
		return fullCost;
	}
	public void setFullCost(int fullCost) {
		this.fullCost = fullCost;
	}
	public List<RemoteItemQuantityDTO> getItems() {
		return items;
	}
	public void setItems(List<RemoteItemQuantityDTO> items) {
		this.items = items;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public DeliveryStateWeb getDeliveryState() {
		return deliveryState;
	}
	public void setDeliveryState(DeliveryStateWeb deliveryState) {
		this.deliveryState = deliveryState;
	}
	
	
}
