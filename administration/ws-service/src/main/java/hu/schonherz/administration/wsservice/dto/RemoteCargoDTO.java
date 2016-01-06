package hu.schonherz.administration.wsservice.dto;

import java.io.Serializable;
import java.util.List;

public class RemoteCargoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3854313547198931721L;
	
	private Long id;
	private Long courierId;
	private String courierName;
	private Long restaurantId;
	private List<RemoteOrderDTO> orders;
	private RemoteCargoState state;
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
	public List<RemoteOrderDTO> getOrders() {
		return orders;
	}
	public void setOrders(List<RemoteOrderDTO> orders) {
		this.orders = orders;
	}
	public RemoteCargoState getState() {
		return state;
	}
	public void setState(RemoteCargoState state) {
		this.state = state;
	}
	public Long getCourierId() {
		return courierId;
	}
	public void setCourierId(Long courierId) {
		this.courierId = courierId;
	}
	public String getCourierName() {
		return courierName;
	}
	public void setCourierName(String courierName) {
		this.courierName = courierName;
	}
	
	

}
