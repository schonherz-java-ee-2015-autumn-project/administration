package hu.schonherz.administration.serviceapi.dto;

import java.io.Serializable;
import java.util.Date;

public class IncomeReportDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8684193417925872499L;

	private Long id;
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

	public void setCreditCard(Integer crediCard) {
		this.creditCard = crediCard;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public void setDefault() {
		this.cash = 0;
		this.courierServiceAmount = 0;
		this.creditCard = 0;
		this.date = new Date();
		this.restaurantTransferAmount = 0;
		this.szepCard = 0;
		this.totalAmount  = 0;
		this.voucher = 0;
		
	}

}
