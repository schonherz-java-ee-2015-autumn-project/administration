package hu.schonherz.administration.service.converter;

import java.util.ArrayList;
import java.util.List;

import hu.schonherz.administration.persistence.entities.Order;
import hu.schonherz.administration.serviceapi.dto.OrderDTO;

public class OrderConverter {

	public static OrderDTO toDTO(Order order) {
		if (order == null) {
			return null;
		}
		OrderDTO result = new OrderDTO();
		result.setAddressToDeliver(order.getAddressToDeliver());
		result.setDeadline(order.getDeadline());
		result.setFullCost(order.getFullCost());
		result.setId(order.getId());
		result.setItems(ItemQuantityConverter.toDTO(order.getItems()));
		result.setPayment(PaymentConverter.toDTO(order.getPayment()));
		result.setDeliveryState(DeliveryStateConverter.toDTO(order.getDeliveryState()));
		return result;
	}

	public static Order toEntity(OrderDTO orderDTO) {
		if (orderDTO == null) {
			return null;
		}
		Order result = new Order();
		result.setAddressToDeliver(orderDTO.getAddressToDeliver());
		result.setDeadline(orderDTO.getDeadline());
		result.setFullCost(orderDTO.getFullCost());
		result.setId(orderDTO.getId());
		result.setItems(ItemQuantityConverter.toEntity(orderDTO.getItems()));
		result.setPayment(PaymentConverter.toEntity(orderDTO.getPayment()));
		result.setDeliveryState(DeliveryStateConverter.toEntity(orderDTO.getDeliveryState()));
		return result;

	}

	public static List<OrderDTO> toDTO(List<Order> order) {
		List<OrderDTO> rv = new ArrayList<>();
		for (Order orders : order) {
			rv.add(toDTO(orders));
		}
		return rv;
	}

	public static List<Order> toEntity(List<OrderDTO> order) {
		List<Order> rv = new ArrayList<>();
		for (OrderDTO orders : order) {
			rv.add(toEntity(orders));
		}
		return rv;
	}

}
