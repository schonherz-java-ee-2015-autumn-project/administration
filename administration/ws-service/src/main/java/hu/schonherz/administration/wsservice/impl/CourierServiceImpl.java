package hu.schonherz.administration.wsservice.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.jws.WebService;

import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.administration.serviceapi.RemoteCargoService;
import hu.schonherz.administration.serviceapi.RemoteOrderService;
import hu.schonherz.administration.serviceapi.RemoteUserService;
import hu.schonherz.administration.serviceapi.dto.CargoDTO;
import hu.schonherz.administration.serviceapi.dto.OrderDTO;
import hu.schonherz.administration.serviceapi.exeption.AddressNotFoundException;
import hu.schonherz.administration.serviceapi.exeption.BusyCourierException;
import hu.schonherz.administration.serviceapi.exeption.CargoAlreadyTakenException;
import hu.schonherz.administration.serviceapi.exeption.CargoNotFoundException;
import hu.schonherz.administration.serviceapi.exeption.CourierNotFoundException;
import hu.schonherz.administration.serviceapi.exeption.OrderIsNotInProgressException;
import hu.schonherz.administration.serviceapi.exeption.WrongCourierException;
import hu.schonherz.administration.wsservice.dto.DeliveryStateWeb;
import hu.schonherz.administration.wsserviceapi.CourierService;
import hu.schonherz.administration.wsserviceapi.converter.RemoteDeliveryStateConverter;

@Stateless(mappedName = "CourierServiceImpl")
@WebService(endpointInterface = "hu.schonherz.administration.wsserviceapi.CourierService", serviceName = "CourierServiceImpl")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class CourierServiceImpl implements CourierService {

	@EJB
	private RemoteCargoService cargoService;

	@EJB
	private RemoteUserService userService;

	@EJB
	private RemoteOrderService orderService;

	@Override
	public void assignCargoToCourier(long cargoId, long courierId) throws CargoAlreadyTakenException, CargoNotFoundException, CourierNotFoundException, BusyCourierException {
		cargoService.assignCargoToCourier(cargoId, courierId);
	}

	@Override
	public void changeDeliveryState(long OrderId, long courierId, DeliveryStateWeb newState)
	        throws CourierNotFoundException, AddressNotFoundException, OrderIsNotInProgressException, WrongCourierException {
		CargoDTO cargo = cargoService.getActiveCargoByCourier(courierId);
		if (cargo != null) {
			List<OrderDTO> orders = cargo.getOrders();
			OrderDTO order = null;
			for (OrderDTO o : orders)
				if (o.getId() == OrderId)
					order = o;
			if (order == null) {
				cargoService.hasOrderId(OrderId, courierId);
				cargoService.hasOrderId(OrderId);
				throw new AddressNotFoundException();
			}
			order.setDeliveryState(RemoteDeliveryStateConverter.toLocal(newState));
			orderService.saveOrder(order);
		}
	}

}
