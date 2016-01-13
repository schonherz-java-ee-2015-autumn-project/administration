package hu.schonherz.administration.serviceapi.dto;

import java.io.Serializable;
import java.util.Date;

public class RestaurantReportDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8684193417925872499L;

	private Long id;
	private Date date;
	private String restaurant;
	private Integer creditCard;
	private Integer szepCard;
	private Integer voucher;
	private Integer cash;
	private Integer totalAmount;
	private Integer restaurantPrice;
	private float restaurantTransferAmount;
	private float servicesPrize;
	
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


	public void setDefault() {
		this.cash = 0;
		this.creditCard = 0;
		this.date = new Date();
		this.restaurant = "";
		this.restaurantTransferAmount = 0;
		this.szepCard = 0;
		this.totalAmount  = 0;
		this.voucher = 0;
		this.restaurantPrice = 0;
		this.servicesPrize = 0;
		
	}

	public String getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}

	public Integer getRestaurantPrice() {
		return restaurantPrice;
	}

	public void setRestaurantPrice(Integer restaurantPrice) {
		this.restaurantPrice = restaurantPrice;
	}

	public float getServicesPrize() {
		return servicesPrize;
	}

	public void setServicesPrize(float servicesPrize) {
		this.servicesPrize = servicesPrize;
	}

}
