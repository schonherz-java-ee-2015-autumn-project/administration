package hu.schonherz.administration.serviceapi.dto;

import java.io.Serializable;
import java.util.List;

public class CargoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3854313547198931721L;
	
	private Long id;
	private Long restaurantId;
	private Long courierId;
	private String restaurantAddresss;
	private String restaurantName;
	private String courierName;
	private String courierPhoneNumber;
	private List<OrderDTO> orders;
	private CargoState state;
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
	public String getRestaurantAddresss() {
		return restaurantAddresss;
	}
	public void setRestaurantAddresss(String restaurantAddresss) {
		this.restaurantAddresss = restaurantAddresss;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getCourierName() {
		return courierName;
	}
	public void setCourierName(String courierName) {
		this.courierName = courierName;
	}
	public String getCourierPhoneNumber() {
		return courierPhoneNumber;
	}
	public void setCourierPhoneNumber(String courierPhoneNumber) {
		this.courierPhoneNumber = courierPhoneNumber;
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
	
	

}
