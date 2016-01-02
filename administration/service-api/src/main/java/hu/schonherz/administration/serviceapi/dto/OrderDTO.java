package hu.schonherz.administration.serviceapi.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class OrderDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7688276666937378335L;
	
	private Long id;
	private String addressToDeliver;
	private Date deadline;
	private PaymentMethod payment;
	private int fullCost;
	private CargoState state;
	private List<ItemQuantityDTO> items;
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
	public PaymentMethod getPayment() {
		return payment;
	}
	public void setPayment(PaymentMethod payment) {
		this.payment = payment;
	}
	public int getFullCost() {
		return fullCost;
	}
	public void setFullCost(int fullCost) {
		this.fullCost = fullCost;
	}
	public CargoState getState() {
		return state;
	}
	public void setState(CargoState state) {
		this.state = state;
	}
	public List<ItemQuantityDTO> getItems() {
		return items;
	}
	public void setItems(List<ItemQuantityDTO> items) {
		this.items = items;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}
