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
	private RemoteCargoState state;
	private List<RemoteItemDTO> items;
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
	public RemoteCargoState getState() {
		return state;
	}
	public void setState(RemoteCargoState state) {
		this.state = state;
	}
	public List<RemoteItemDTO> getItems() {
		return items;
	}
	public void setItems(List<RemoteItemDTO> items) {
		this.items = items;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}
