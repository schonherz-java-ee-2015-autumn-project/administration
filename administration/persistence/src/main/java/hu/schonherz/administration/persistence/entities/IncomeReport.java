package hu.schonherz.administration.persistence.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

@Entity
public class IncomeReport extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8684193417925872499L;

	private Date date;
	private Integer creditCard;
	private Integer szepCard;
	private Integer voucher;
	private Integer cash;
	private Integer totalAmount;
	private float restaurantTransferAmount;
	private float courierServiceAmount;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(Integer creditCard) {
		this.creditCard = creditCard;
	}

	public Integer getSzepCard() {
		return szepCard;
	}

	public void setSzepCard(Integer szepCard) {
		this.szepCard = szepCard;
	}

	public Integer getVoucher() {
		return voucher;
	}

	public void setVoucher(Integer voucher) {
		this.voucher = voucher;
	}

	public Integer getCash() {
		return cash;
	}

	public void setCash(Integer cash) {
		this.cash = cash;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public float getRestaurantTransferAmount() {
		return restaurantTransferAmount;
	}

	public void setRestaurantTransferAmount(float restaurantTransferAmount) {
		this.restaurantTransferAmount = restaurantTransferAmount;
	}

	public float getCourierServiceAmount() {
		return courierServiceAmount;
	}

	public void setCourierServiceAmount(float courierServiceAmount) {
		this.courierServiceAmount = courierServiceAmount;
	}

}
