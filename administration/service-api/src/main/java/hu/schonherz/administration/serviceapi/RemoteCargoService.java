package hu.schonherz.administration.serviceapi;

import java.util.List;

import hu.schonherz.administration.serviceapi.dto.CargoDTO;
import hu.schonherz.administration.serviceapi.exeption.InvalidFieldValuesException;

public interface RemoteCargoService {
	
	void saveCargo(CargoDTO cargo) throws InvalidFieldValuesException;
	List<CargoDTO> getCargos();

}
