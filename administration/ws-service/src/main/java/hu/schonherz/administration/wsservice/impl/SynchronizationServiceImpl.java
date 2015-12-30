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
import hu.schonherz.administration.serviceapi.dto.ItemDTO;
import hu.schonherz.administration.serviceapi.dto.UserRole;
import hu.schonherz.administration.serviceapi.exeption.InvalidFieldValuesException;
import hu.schonherz.administration.serviceapi.exeption.NotAllowedRoleException;
import hu.schonherz.administration.wsservice.dto.RemoteCargoDTO;
import hu.schonherz.administration.wsservice.dto.RemoteItemDTO;
import hu.schonherz.administration.wsservice.dto.RemoteOrderDTO;
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
		List<RemoteOrderDTO> orders = new ArrayList<>();
		for (RemoteOrderDTO order : cargo.getOrders()) {
			List<RemoteItemDTO> items = new ArrayList<>();
			for (RemoteItemDTO item : order.getItems()) {
				items.add(RemoteItemConverter.toRemoteDTO(remoteItemService.saveItem(RemoteItemConverter.toDTO(item))));
			}
			order.setItems(items);
			orders.add(
					RemoteOrderConverter.toRemoteDTO(remoteOrderService.saveOrder(RemoteOrderConverter.toDTO(order))));

		}
		cargo.setOrders(orders);
		remoteCargoService.saveCargo(RemoteCargoConverter.toDTO(cargo));
	}

}
