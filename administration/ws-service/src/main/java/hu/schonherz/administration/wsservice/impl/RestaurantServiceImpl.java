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
				itemQuantity = RemoteItemQuantityConverter.toRemoteDTO(remoteItemQuantityService.saveItemQuantity(RemoteItemQuantityConverter.toDTO(itemQuantity)));
				items.add(itemQuantity);
			}
			order.setItems(items);
			orders.add(
					RemoteOrderConverter.toRemoteDTO(remoteOrderService.saveOrder(RemoteOrderConverter.toDTO(order))));

		}
		cargo.setOrders(orders);
		remoteCargoService.saveCargo(RemoteCargoConverter.toDTO(cargo));
	}

}
