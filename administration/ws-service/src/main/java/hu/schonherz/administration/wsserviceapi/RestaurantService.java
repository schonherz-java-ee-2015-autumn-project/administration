package hu.schonherz.administration.wsserviceapi;

import javax.jws.WebMethod;
import javax.jws.WebService;

import hu.schonherz.administration.serviceapi.exeption.CargoNotFoundException;
import hu.schonherz.administration.serviceapi.exeption.InvalidFieldValuesException;
import hu.schonherz.administration.serviceapi.exeption.InvalidModifyStateException;
import hu.schonherz.administration.serviceapi.exeption.ModifyWithoutIdException;
import hu.schonherz.administration.serviceapi.exeption.OrderNotFoundException;
import hu.schonherz.administration.wsservice.dto.RemoteCargoDTO;

@WebService
public interface RestaurantService {
	@WebMethod(operationName = "saveCargo")
	RemoteCargoDTO saveCargo(RemoteCargoDTO cargo) throws InvalidFieldValuesException;

	@WebMethod(operationName = "modifyCargo")
	RemoteCargoDTO modifyCargo(RemoteCargoDTO cargo)
			throws CargoNotFoundException, InvalidFieldValuesException, ModifyWithoutIdException, OrderNotFoundException, InvalidModifyStateException;

}
