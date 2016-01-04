package hu.schonherz.administration.wsserviceapi;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import hu.schonherz.administration.serviceapi.exeption.BusyCourierException;
import hu.schonherz.administration.serviceapi.exeption.CargoAlreadyTakenException;
import hu.schonherz.administration.serviceapi.exeption.CargoNotFoundException;
import hu.schonherz.administration.serviceapi.exeption.CourierNotFoundException;
import hu.schonherz.administration.serviceapi.exeption.IllegalStateTransitionException;
import hu.schonherz.administration.serviceapi.exeption.InvalidFieldValuesException;
import hu.schonherz.administration.serviceapi.exeption.NotAllOrderCompletedException;
import hu.schonherz.administration.wsservice.dto.RemoteCargoState;

@WebService
public interface CourierService {

	@WebMethod(operationName = "assignCargoToCourier")
	public void assignCargoToCourier(@WebParam(name = "cargoID") long cargoId,
			@WebParam(name = "courierID") long courierId) throws CargoAlreadyTakenException, CargoNotFoundException,
					CourierNotFoundException, BusyCourierException, InvalidFieldValuesException;

	@WebMethod(operationName = "changeCargoState")
	public void changeCargoState(@WebParam(name = "CargoID") long cargoId, @WebParam(name = "CourierID") long courierId,
			@WebParam(name = "CargoState") RemoteCargoState state)
					throws CargoNotFoundException, CargoAlreadyTakenException, IllegalStateTransitionException,
					CourierNotFoundException, NotAllOrderCompletedException, InvalidFieldValuesException;

}