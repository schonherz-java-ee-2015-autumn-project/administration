package hu.schonherz.administration.wsservice.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.jws.WebService;

import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.administration.serviceapi.RemoteCargoService;
import hu.schonherz.administration.serviceapi.exeption.AddressNotFoundException;
import hu.schonherz.administration.serviceapi.exeption.BusyCourierException;
import hu.schonherz.administration.serviceapi.exeption.CargoAlreadyTakenException;
import hu.schonherz.administration.serviceapi.exeption.CargoNotFoundException;
import hu.schonherz.administration.serviceapi.exeption.CourierNotFoundException;
import hu.schonherz.administration.serviceapi.exeption.OrderException;
import hu.schonherz.administration.wsservice.dto.RemotePaymentMethod;
import hu.schonherz.administration.wsserviceapi.CourierService;
import hu.schonherz.administration.wsserviceapi.converter.RemotePaymentConverter;

@Stateless(mappedName = "CourierServiceImpl")
@WebService(endpointInterface = "hu.schonherz.administration.wsserviceapi.CourierService", serviceName = "CourierServiceImpl")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class CourierServiceImpl implements CourierService {
	
	@EJB
	private RemoteCargoService cargoService;
	
	@Override
	public void assignCargoToCourier(long cargoId, long courierId)
			throws CargoAlreadyTakenException, CargoNotFoundException, CourierNotFoundException, BusyCourierException {
		cargoService.assignCargoToCourier(cargoId, courierId);
	}

	@Override
	public void changePaymentState(Long courierId, Long orderId,RemotePaymentMethod paymentMethod)
			throws CourierNotFoundException, CargoNotFoundException, OrderException, AddressNotFoundException {
		cargoService.changePaymentState(courierId,orderId,RemotePaymentConverter.toDTO(paymentMethod));
		
	}

}
