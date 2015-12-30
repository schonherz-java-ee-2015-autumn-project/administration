package hu.schonherz.administration.wsserviceapi.converter;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.schonherz.administration.serviceapi.dto.OrderDTO;
import hu.schonherz.administration.wsservice.dto.RemoteOrderDTO;

public class RemoteOrderConverter {
	static Mapper mapper = new DozerBeanMapper();

	public static OrderDTO toDTO(RemoteOrderDTO order) {
		if (order == null) {
			return null;
		}
		return mapper.map(order, OrderDTO.class);
	}

	public static RemoteOrderDTO toRemoteDTO(OrderDTO OrderDTO) {
		if (OrderDTO == null) {
			return null;
		}
		return mapper.map(OrderDTO, RemoteOrderDTO.class);
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

