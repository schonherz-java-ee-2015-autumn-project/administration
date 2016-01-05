package hu.schonherz.administration.serviceapi.dto;

import java.io.Serializable;
import java.util.Date;

public class CourierIncomeDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 336503280844456579L;
	private Long id;
	private Long courierId;
	private String courierName;
	private Date date;
	private Integer cash;
	private Integer SZÉPCard;
	private Integer crediCard;
	private Integer voucher;
	private Integer actualCash;
	private Integer actualVoucher;
	
	public String getCourierName() {
		return courierName;
	}
	public void setCourierName(String courierName) {
		this.courierName = courierName;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getCash() {
		return cash;
	}
	public void setCash(Integer cash) {
		this.cash = cash;
	}
	public Integer getSZÉPCard() {
		return SZÉPCard;
	}
	public void setSZÉPCard(Integer SZÉPCard) {
		this.SZÉPCard = SZÉPCard;
	}
	public Integer getCrediCard() {
		return crediCard;
	}
	public void setCrediCard(Integer crediCard) {
		this.crediCard = crediCard;
	}
	public Integer getVoucher() {
		return voucher;
	}
	public void setVoucher(Integer voucher) {
		this.voucher = voucher;
	}
	public Integer getActualCash() {
		return actualCash;
	}
	public void setActualCash(Integer actualCash) {
		this.actualCash = actualCash;
	}
	public Integer getActualVoucher() {
		return actualVoucher;
	}
	public void setActualVoucher(Integer actualVoucher) {
		this.actualVoucher = actualVoucher;
	}
	public Long getCourierId() {
		return courierId;
	}
	public void setCourierId(Long courierId) {
		this.courierId = courierId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	
	

}
