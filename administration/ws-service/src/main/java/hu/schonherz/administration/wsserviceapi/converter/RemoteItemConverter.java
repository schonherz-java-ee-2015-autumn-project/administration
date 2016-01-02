package hu.schonherz.administration.wsserviceapi.converter;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.schonherz.administration.serviceapi.dto.ItemDTO;
import hu.schonherz.administration.wsservice.dto.RemoteItemDTO;

public class RemoteItemConverter {

	static Mapper mapper = new DozerBeanMapper();

	public static ItemDTO toDTO(RemoteItemDTO item) {
		if (item == null) {
			return null;
		}
		return mapper.map(item, ItemDTO.class);
	}

	public static RemoteItemDTO toRemoteDTO(ItemDTO ItemDTO) {
		if (ItemDTO == null) {
			return null;
		}
		return mapper.map(ItemDTO, RemoteItemDTO.class);
	}

	public static List<RemoteItemDTO> toRemoteDTO(List<ItemDTO> items) {
		List<RemoteItemDTO> rv = new ArrayList<>();
		for (ItemDTO item : items) {
			rv.add(toRemoteDTO(item));
		}
		return rv;
	}

	public static List<ItemDTO> toDTO(List<RemoteItemDTO> items) {
		List<ItemDTO> rv = new ArrayList<>();
		for (RemoteItemDTO item : items) {
			rv.add(toDTO(item));
		}
		return rv;
	}

}
