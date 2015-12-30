package hu.schonherz.administration.serviceapi;

import java.util.List;

import hu.schonherz.administration.serviceapi.dto.OrderDTO;

public interface RemoteOrderService {
	void saveOrder(OrderDTO order);
	List<OrderDTO> getOrders();
}
