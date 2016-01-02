package hu.schonherz.administration.wsserviceapi;

import javax.jws.WebMethod;
import javax.jws.WebService;

import hu.schonherz.administration.serviceapi.exeption.InvalidFieldValuesException;
import hu.schonherz.administration.wsservice.dto.RemoteCargoDTO;

@WebService
public interface RestaurantService {
	@WebMethod(operationName = "saveCargo")
	void saveCargo(RemoteCargoDTO cargo) throws InvalidFieldValuesException;
	
}
