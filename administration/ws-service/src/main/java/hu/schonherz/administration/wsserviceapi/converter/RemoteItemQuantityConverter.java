package hu.schonherz.administration.wsserviceapi.converter;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.schonherz.administration.serviceapi.dto.ItemQuantityDTO;
import hu.schonherz.administration.wsservice.dto.RemoteItemQuantityDTO;

public class RemoteItemQuantityConverter {
	static Mapper mapper = new DozerBeanMapper();

	public static RemoteItemQuantityDTO toRemoteDTO(ItemQuantityDTO item) {
		if (item == null) {
			return null;
		}
		return mapper.map(item, RemoteItemQuantityDTO.class);
	}

	public static ItemQuantityDTO toDTO(RemoteItemQuantityDTO RemoteItemQuantityDTO) {
		if (RemoteItemQuantityDTO == null) {
			return null;
		}
		return mapper.map(RemoteItemQuantityDTO, ItemQuantityDTO.class);
	}

	public static List<RemoteItemQuantityDTO> toRemoteDTO(List<ItemQuantityDTO> item) {
		List<RemoteItemQuantityDTO> rv = new ArrayList<>();
		for (ItemQuantityDTO items : item) {
			rv.add(toRemoteDTO(items));
		}
		return rv;
	}

	public static List<ItemQuantityDTO> toDTO(List<RemoteItemQuantityDTO> item) {
		List<ItemQuantityDTO> rv = new ArrayList<>();
		for (RemoteItemQuantityDTO items : item) {
			rv.add(toDTO(items));
		}
		return rv;
	}
}
