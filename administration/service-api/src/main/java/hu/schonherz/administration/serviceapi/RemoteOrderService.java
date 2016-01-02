package hu.schonherz.administration.serviceapi;

import java.util.List;

import hu.schonherz.administration.serviceapi.dto.OrderDTO;

public interface RemoteOrderService {
	OrderDTO saveOrder(OrderDTO order);
	List<OrderDTO> getOrders();
}
