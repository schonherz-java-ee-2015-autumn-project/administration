package hu.schonherz.administration.service.converter;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.schonherz.administration.persistence.entities.ItemQuantity;
import hu.schonherz.administration.serviceapi.dto.ItemQuantityDTO;

public class ItemQuantityConverter {
	static Mapper mapper = new DozerBeanMapper();

	public static ItemQuantityDTO toDTO(ItemQuantity item) {
		if (item == null) {
			return null;
		}
		ItemQuantityDTO result = new ItemQuantityDTO();
		result.setId(item.getId());
		result.setItem(ItemConverter.toDTO(item.getItem()));
		result.setQuantity(item.getQuantity());
		return result;
	}

	public static ItemQuantity toEntity(ItemQuantityDTO ItemQuantityDTO) {
		if (ItemQuantityDTO == null) {
			return null;
		}
		ItemQuantity result = new ItemQuantity();
		result.setId(ItemQuantityDTO.getId());
		result.setItem(ItemConverter.toEntity(ItemQuantityDTO.getItem()));
		result.setQuantity(ItemQuantityDTO.getQuantity());
		return result;
	}

	public static List<ItemQuantityDTO> toDTO(List<ItemQuantity> item) {
		List<ItemQuantityDTO> rv = new ArrayList<>();
		for (ItemQuantity items : item) {
			rv.add(toDTO(items));
		}
		return rv;
	}

	public static List<ItemQuantity> toEntity(List<ItemQuantityDTO> item) {
		List<ItemQuantity> rv = new ArrayList<>();
		for (ItemQuantityDTO items : item) {
			rv.add(toEntity(items));
		}
		return rv;
	}
}
