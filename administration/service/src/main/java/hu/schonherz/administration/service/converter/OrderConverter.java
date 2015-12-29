package hu.schonherz.administration.service.converter;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.schonherz.administration.persistence.entities.Order;
import hu.schonherz.administration.serviceapi.dto.OrderDTO;

public class OrderConverter {
	static Mapper mapper = new DozerBeanMapper();

	public static OrderDTO toDTO(Order order) {
		if (order == null) {
			return null;
		}
		return mapper.map(order, OrderDTO.class);
	}

	public static Order toEntity(OrderDTO OrderDTO) {
		if (OrderDTO == null) {
			return null;
		}
		return mapper.map(OrderDTO, Order.class);
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
