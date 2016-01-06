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
import hu.schonherz.administration.serviceapi.exeption.AddressNotFoundException;
import hu.schonherz.administration.serviceapi.exeption.BusyCourierException;
import hu.schonherz.administration.serviceapi.exeption.CargoAlreadyTakenException;
import hu.schonherz.administration.serviceapi.exeption.CargoNotFoundException;
import hu.schonherz.administration.serviceapi.exeption.CourierNotFoundException;
import hu.schonherz.administration.serviceapi.exeption.IllegalStateTransitionException;
import hu.schonherz.administration.serviceapi.exeption.InvalidFieldValuesException;
import hu.schonherz.administration.serviceapi.exeption.NotAllOrderCompletedException;
import hu.schonherz.administration.serviceapi.exeption.OrderException;
import hu.schonherz.administration.serviceapi.exeption.OrderIsNotInProgressException;
import hu.schonherz.administration.serviceapi.exeption.WrongCourierException;
import hu.schonherz.administration.wsservice.dto.DeliveryStateWeb;
import hu.schonherz.administration.wsservice.dto.RemoteCargoDTO;
import hu.schonherz.administration.wsservice.dto.RemoteCargoState;
import hu.schonherz.administration.wsservice.dto.RemoteOrderDTO;
import hu.schonherz.administration.wsservice.dto.RemotePaymentMethod;
import hu.schonherz.administration.wsserviceapi.CourierService;
import hu.schonherz.administration.wsserviceapi.converter.RemoteCargoConverter;
import hu.schonherz.administration.wsserviceapi.converter.RemoteCargoStateConverter;
import hu.schonherz.administration.wsserviceapi.converter.RemoteOrderConverter;
import hu.schonherz.administration.wsserviceapi.converter.RemotePaymentConverter;

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
	public void assignCargoToCourier(long cargoId, long courierId) throws CargoAlreadyTakenException,
			CargoNotFoundException, CourierNotFoundException, BusyCourierException, InvalidFieldValuesException {
		cargoService.assignCargoToCourier(cargoId, courierId);
	}

	@Override

	public void changeDeliveryState(long OrderId, long courierId, DeliveryStateWeb newState)
			throws CourierNotFoundException, AddressNotFoundException, OrderIsNotInProgressException,
			WrongCourierException, IllegalStateTransitionException {
		RemoteCargoDTO cargo = RemoteCargoConverter.toRemoteDTO(cargoService.getActiveCargoByCourier(courierId));
		if (cargo != null) {
			List<RemoteOrderDTO> orders = cargo.getOrders();
			RemoteOrderDTO order = null;
			for (RemoteOrderDTO o : orders)
				if (o.getId() == OrderId)
					order = o;
			if (order == null) {
				throw new AddressNotFoundException("Address not found in active cargos.");
			}
			if (order.getDeliveryState().equals(DeliveryStateWeb.In_progress)) {
				order.setDeliveryState(newState);
				orderService.saveOrder(RemoteOrderConverter.toDTO(order));
			} else {
				throw new IllegalStateTransitionException();
			}
		} else {
			throw new AddressNotFoundException("This courier has no active cargos.");
		}
	}

	public void changeCargoState(long cargoId, long courierId, RemoteCargoState state)
			throws CargoNotFoundException, CargoAlreadyTakenException, IllegalStateTransitionException,
			CourierNotFoundException, NotAllOrderCompletedException, InvalidFieldValuesException {
		cargoService.changeCargoState(cargoId, courierId, RemoteCargoStateConverter.toLocal(state));
	}

	@Override
	public void changePaymentState(Long courierId, Long orderId, RemotePaymentMethod paymentMethod)
			throws CourierNotFoundException, CargoNotFoundException, OrderException, AddressNotFoundException {
		cargoService.changePaymentState(courierId, orderId, RemotePaymentConverter.toDTO(paymentMethod));

	}

}
