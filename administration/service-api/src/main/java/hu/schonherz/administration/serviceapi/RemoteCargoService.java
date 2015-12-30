package hu.schonherz.administration.serviceapi;

import java.util.List;

import hu.schonherz.administration.serviceapi.dto.CargoDTO;

public interface RemoteCargoService {
	
	void saveCargo(CargoDTO cargo);
	List<CargoDTO> getCargos();

}
