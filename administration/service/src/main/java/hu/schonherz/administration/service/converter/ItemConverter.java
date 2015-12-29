package hu.schonherz.administration.service.converter;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.schonherz.administration.persistence.entities.Item;
import hu.schonherz.administration.serviceapi.dto.ItemDTO;

public class ItemConverter {

	static Mapper mapper = new DozerBeanMapper();

	public static ItemDTO toDTO(Item item) {
		if (item == null) {
			return null;
		}
		return mapper.map(item, ItemDTO.class);
	}

	public static Item toEntity(ItemDTO ItemDTO) {
		if (ItemDTO == null) {
			return null;
		}
		return mapper.map(ItemDTO, Item.class);
	}

	public static List<ItemDTO> toDTO(List<Item> item) {
		List<ItemDTO> rv = new ArrayList<>();
		for (Item items : item) {
			rv.add(toDTO(items));
		}
		return rv;
	}

	public static List<Item> toEntity(List<ItemDTO> item) {
		List<Item> rv = new ArrayList<>();
		for (ItemDTO items : item) {
			rv.add(toEntity(items));
		}
		return rv;
	}

}
