package hu.schonherz.administration.wsserviceapi.converter;

import java.util.ArrayList;
import java.util.List;

import hu.schonherz.administration.serviceapi.dto.OrderDTO;
import hu.schonherz.administration.wsservice.dto.RemoteOrderDTO;

public class RemoteOrderConverter {

	public static OrderDTO toDTO(RemoteOrderDTO order) {
		if (order == null) {
			return null;
		}
		OrderDTO result = new OrderDTO();
		result.setAddressToDeliver(order.getAddressToDeliver());
		result.setDeadline(order.getDeadline());
		result.setFullCost(order.getFullCost());
		result.setId(order.getId());
		result.setItems(RemoteItemQuantityConverter.toDTO(order.getItems()));
		result.setPayment(RemotePaymentConverter.toDTO(order.getPayment()));
		result.setDeliveryState(RemoteDeliveryStateConverter.toLocal(order.getDeliveryState()));
		return result;
	}

	public static RemoteOrderDTO toRemoteDTO(OrderDTO orderDTO) {
		if (orderDTO == null) {
			return null;
		}
		RemoteOrderDTO result = new RemoteOrderDTO();
		result.setAddressToDeliver(orderDTO.getAddressToDeliver());
		result.setDeadline(orderDTO.getDeadline());
		result.setFullCost(orderDTO.getFullCost());
		result.setId(orderDTO.getId());
		result.setItems(RemoteItemQuantityConverter.toRemoteDTO(orderDTO.getItems()));
		result.setPayment(RemotePaymentConverter.toRemoteDTO(orderDTO.getPayment()));
		result.setDeliveryState(RemoteDeliveryStateConverter.toRemote(orderDTO.getDeliveryState()));
		return result;
	}

	public static List<RemoteOrderDTO> toRemoteDTO(List<OrderDTO> orders) {
		List<RemoteOrderDTO> rv = new ArrayList<>();
		for (OrderDTO order : orders) {
			rv.add(toRemoteDTO(order));
		}
		return rv;
	}

	public static List<OrderDTO> toDTO(List<RemoteOrderDTO> orders) {
		List<OrderDTO> rv = new ArrayList<>();
		for (RemoteOrderDTO order : orders) {
			rv.add(toDTO(order));
		}
		return rv;
	}

}

