package hu.schonherz.administration.serviceapi;

import java.util.List;

import hu.schonherz.administration.serviceapi.dto.ItemDTO;

public interface RemoteItemService {
	
	ItemDTO saveItem(ItemDTO item);
	
	List<ItemDTO> getItems();

}
