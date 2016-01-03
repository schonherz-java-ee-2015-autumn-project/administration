package hu.schonherz.administration.wsserviceapi;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import hu.schonherz.administration.persistence.entities.helper.DeliveryState;
import hu.schonherz.administration.serviceapi.exeption.AddressNotFoundException;
import hu.schonherz.administration.serviceapi.exeption.BusyCourierException;
import hu.schonherz.administration.serviceapi.exeption.CargoAlreadyTakenException;
import hu.schonherz.administration.serviceapi.exeption.CargoNotFoundException;
import hu.schonherz.administration.serviceapi.exeption.CourierNotFoundException;
import hu.schonherz.administration.serviceapi.exeption.OrderIsNotInProgressException;
import hu.schonherz.administration.serviceapi.exeption.WrongCourierException;

@WebService
public interface CourierService {

	@WebMethod(operationName = "assignCargoToCourier")
	public void assignCargoToCourier(@WebParam(name = "cargoID") long cargoId,
			@WebParam(name = "courierID") long courierId) throws CargoAlreadyTakenException, CargoNotFoundException,
					CourierNotFoundException, BusyCourierException;

	@WebMethod(operationName = "ChangeDeliveryState")
	public void ChangeDeliveryState(@WebParam(name = "OrderID") long OrderId,@WebParam(name = "courierID") long courierId
			,@WebParam(name = "NewDeliveryState")DeliveryState newState) throws CourierNotFoundException,AddressNotFoundException,
	OrderIsNotInProgressException,WrongCourierException;


}

