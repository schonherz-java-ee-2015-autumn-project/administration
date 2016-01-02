package hu.schonherz.administration.wsservice.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.jws.WebService;

import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.administration.serviceapi.RemoteCargoService;
import hu.schonherz.administration.serviceapi.RemoteItemQuantityService;
import hu.schonherz.administration.serviceapi.RemoteItemService;
import hu.schonherz.administration.serviceapi.RemoteOrderService;
import hu.schonherz.administration.serviceapi.dto.ItemQuantityDTO;
import hu.schonherz.administration.serviceapi.dto.OrderDTO;
import hu.schonherz.administration.serviceapi.exeption.InvalidFieldValuesException;
import hu.schonherz.administration.wsservice.dto.RemoteCargoDTO;
import hu.schonherz.administration.wsservice.dto.RemoteItemDTO;
import hu.schonherz.administration.wsservice.dto.RemoteItemQuantityDTO;
import hu.schonherz.administration.wsservice.dto.RemoteOrderDTO;
import hu.schonherz.administration.wsserviceapi.RestaurantService;
import hu.schonherz.administration.wsserviceapi.converter.RemoteCargoConverter;
import hu.schonherz.administration.wsserviceapi.converter.RemoteItemConverter;
import hu.schonherz.administration.wsserviceapi.converter.RemoteItemQuantityConverter;
import hu.schonherz.administration.wsserviceapi.converter.RemoteOrderConverter;



@Stateless(mappedName = "RestaurantServiceImpl")
@WebService(endpointInterface = "hu.schonherz.administration.wsserviceapi.RestaurantService", serviceName = "RestaurantServiceImpl")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class RestaurantServiceImpl implements RestaurantService {

	@EJB
	private RemoteCargoService remoteCargoService;

	@EJB
	private RemoteItemService remoteItemService;

	@EJB
	private RemoteOrderService remoteOrderService;
	
	@EJB
	private RemoteItemQuantityService remoteItemQuantityService;

	@Override
	public void saveCargo(RemoteCargoDTO cargo) throws InvalidFieldValuesException {
		List<RemoteOrderDTO> orders = new ArrayList<>();
		for (RemoteOrderDTO order : cargo.getOrders()) {
			List<RemoteItemQuantityDTO> items = new ArrayList<>();
			for (RemoteItemQuantityDTO item : order.getItems()) {
				RemoteItemDTO i = RemoteItemConverter.toRemoteDTO(remoteItemService.saveItem(RemoteItemConverter.toDTO(item.getItemDTO())));
				RemoteItemQuantityDTO itemQuantity = item;
				itemQuantity.setItemDTO(i);
				ItemQuantityDTO iqdto = RemoteItemQuantityConverter.toDTO(itemQuantity);
				ItemQuantityDTO result = remoteItemQuantityService.saveItemQuantity(iqdto);
				RemoteItemQuantityDTO itemQuantity2 = RemoteItemQuantityConverter.toRemoteDTO(result);
				items.add(itemQuantity2);
			}
			order.setItems(items);
			OrderDTO oDTO = RemoteOrderConverter.toDTO(order);
			OrderDTO oDTO2 = remoteOrderService.saveOrder(oDTO);
			RemoteOrderDTO roDTO = RemoteOrderConverter.toRemoteDTO(oDTO2);
			orders.add(roDTO);
					}
		cargo.setOrders(orders);
		remoteCargoService.saveCargo(RemoteCargoConverter.toDTO(cargo));
	}

}
