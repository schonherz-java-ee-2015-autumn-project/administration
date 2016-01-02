package hu.schonherz.administration.persistence.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;

import hu.schonherz.administration.persistence.entities.helper.DeliveryState;
import hu.schonherz.administration.persistence.entities.helper.Payment;

@Entity(name = "restaurant_order")
public class Order extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8878384846302993044L;

	@ManyToMany
	private List<ItemQuantity> items;
	private String addressToDeliver;
	private Date deadline;
	@Enumerated(EnumType.STRING)
	private Payment payment;
	private int fullCost;
	@Enumerated(EnumType.STRING)
	private DeliveryState deliveryState;

	public DeliveryState getDeliveryState() {
		return deliveryState;
	}

	public void setDeliveryState(DeliveryState deliveryState) {
		this.deliveryState = deliveryState;
	}

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

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public int getFullCost() {
		return fullCost;
	}

	public void setFullCost(int fullCost) {
		this.fullCost = fullCost;
	}

	public List<ItemQuantity> getItems() {
		return items;
	}

	public void setItems(List<ItemQuantity> items) {
		this.items = items;
	}
}