package hu.schonherz.administration.serviceapi.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CargoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3854313547198931721L;
	
	private Long id;
	private Long restaurantId;
	private Long courierId;
	private String courierName;
	private List<OrderDTO> orders;
	private CargoState state;
	private Date date;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}
	public Long getCourierId() {
		return courierId;
	}
	public void setCourierId(Long courierId) {
		this.courierId = courierId;
	}
	public List<OrderDTO> getOrders() {
		return orders;
	}
	public void setOrders(List<OrderDTO> orders) {
		this.orders = orders;
	}
	public CargoState getState() {
		return state;
	}
	public void setState(CargoState state) {
		this.state = state;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getCourierName() {
		return courierName;
	}
	public void setCourierName(String courierName) {
		this.courierName = courierName;
	}
	
	

}
