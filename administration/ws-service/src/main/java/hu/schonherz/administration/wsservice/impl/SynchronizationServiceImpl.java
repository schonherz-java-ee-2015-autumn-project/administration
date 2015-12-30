package hu.schonherz.administration.wsservice.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.jws.WebService;

import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.administration.serviceapi.RemoteCargoService;
import hu.schonherz.administration.serviceapi.RemoteItemService;
import hu.schonherz.administration.serviceapi.RemoteOrderService;
import hu.schonherz.administration.serviceapi.RemoteUserService;
import hu.schonherz.administration.serviceapi.dto.UserRole;
import hu.schonherz.administration.serviceapi.exeption.InvalidFieldValuesException;
import hu.schonherz.administration.serviceapi.exeption.NotAllowedRoleException;
import hu.schonherz.administration.wsservice.dto.RemoteCargoDTO;
import hu.schonherz.administration.wsservice.dto.RemoteCargoState;
import hu.schonherz.administration.wsservice.dto.RemoteItemDTO;
import hu.schonherz.administration.wsservice.dto.RemoteOrderDTO;
import hu.schonherz.administration.wsservice.dto.RemotePaymentMethod;
import hu.schonherz.administration.wsservice.dto.WebUserDTO;
import hu.schonherz.administration.wsserviceapi.SynchronizationService;
import hu.schonherz.administration.wsserviceapi.converter.RemoteCargoConverter;
import hu.schonherz.administration.wsserviceapi.converter.RemoteItemConverter;
import hu.schonherz.administration.wsserviceapi.converter.RemoteOrderConverter;
import hu.schonherz.administration.wsserviceapi.converter.UserConverter;

@Stateless(mappedName = "SynchronizationServiceImpl")
@WebService(endpointInterface = "hu.schonherz.administration.wsserviceapi.SynchronizationService", serviceName = "SynchronizationServiceImpl")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class SynchronizationServiceImpl implements SynchronizationService {

	@EJB
	private RemoteUserService remoteUserService;
	
	@EJB
	private RemoteCargoService remoteCargoService;
	
	@EJB
	private RemoteItemService remoteItemService;
	
	@EJB
	private RemoteOrderService remoteOrderService;
	
	@Override
	public List<WebUserDTO> getUsers(UserRole role) throws NotAllowedRoleException {
		return UserConverter.toWebUserDTO(remoteUserService.getUsers(role));
	}


	@Override
	public List<WebUserDTO> getUsersByDate(UserRole role, Date lastModified) throws NotAllowedRoleException {
		return UserConverter.toWebUserDTO(remoteUserService.getUsers(role, lastModified));
	}


	@Override
	public void saveCargo(RemoteCargoDTO cargo) throws InvalidFieldValuesException {
		RemoteItemDTO item = new RemoteItemDTO();
		item.setCount(5);
		item.setName("Spártai");
		item.setPrice(1300);
		
		item = RemoteItemConverter.toRemoteDTO(remoteItemService.saveItem(RemoteItemConverter.toDTO(item)));
		RemoteItemDTO item2 = new RemoteItemDTO();
		item2.setCount(1);
		item2.setName("Leves");
		item2.setPrice(999);
		

		item2 = RemoteItemConverter.toRemoteDTO(remoteItemService.saveItem(RemoteItemConverter.toDTO(item2)));
		List<RemoteItemDTO> items = new ArrayList<>();
		
		items.add(item);
		items.add(item2);
		
		RemoteOrderDTO order = new RemoteOrderDTO();
		order.setItems(items);
		
		order.setAddressToDeliver("Nagyon utca 23.");
		order.setDeadline( new Date());
		order.setFullCost(35325);
		order.setState(RemoteCargoState.Free);
		order.setPayment(RemotePaymentMethod.Cash);


		order = RemoteOrderConverter.toRemoteDTO(remoteOrderService.saveOrder(RemoteOrderConverter.toDTO(order)));
		
		RemoteCargoDTO cargoDTO = new RemoteCargoDTO();
		
		List<RemoteOrderDTO> orders = new ArrayList<>();
		orders.add(order);
		
		cargoDTO.setOrders(orders);
		cargoDTO.setRestaurantAddresss("Étterem cím");
		cargoDTO.setRestaurantId(12L);
		cargoDTO.setRestaurantName("étterem 5");
		cargoDTO.setState(RemoteCargoState.Free);
		
		
		remoteCargoService.saveCargo(RemoteCargoConverter.toDTO(cargoDTO));
	}

}
