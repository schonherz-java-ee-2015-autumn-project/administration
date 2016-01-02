package hu.schonherz.administration.serviceapi;

import java.util.List;

import hu.schonherz.administration.serviceapi.dto.ItemQuantityDTO;

public interface RemoteItemQuantityService {
	
	ItemQuantityDTO saveItemQuantity(ItemQuantityDTO item);
	List<ItemQuantityDTO> getItemQuantities();
	ItemQuantityDTO findById(Long id);
}
