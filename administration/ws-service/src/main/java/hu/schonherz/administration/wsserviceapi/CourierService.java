package hu.schonherz.administration.wsserviceapi;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import hu.schonherz.administration.serviceapi.exeption.BusyCourierException;
import hu.schonherz.administration.serviceapi.exeption.CargoAlreadyTakenException;
import hu.schonherz.administration.serviceapi.exeption.CargoNotFoundException;
import hu.schonherz.administration.serviceapi.exeption.CourierNotFoundException;

@WebService
public interface CourierService {

	@WebMethod(operationName = "assignCargoToCourier")
	public void assignCargoToCourier(@WebParam(name = "cargoID") long cargoId,
			@WebParam(name = "courierID") long courierId) throws CargoAlreadyTakenException, CargoNotFoundException,
					CourierNotFoundException, BusyCourierException;

}