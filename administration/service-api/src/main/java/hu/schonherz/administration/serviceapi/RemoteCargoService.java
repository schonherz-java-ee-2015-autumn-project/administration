package hu.schonherz.administration.serviceapi;

import java.util.Date;
import java.util.List;

import hu.schonherz.administration.serviceapi.dto.CargoDTO;

import hu.schonherz.administration.serviceapi.exeption.InvalidDateException;
import hu.schonherz.administration.serviceapi.exeption.BusyCourierException;
import hu.schonherz.administration.serviceapi.exeption.CargoAlreadyTakenException;
import hu.schonherz.administration.serviceapi.exeption.CargoNotFoundException;
import hu.schonherz.administration.serviceapi.exeption.CourierNotFoundException;
import hu.schonherz.administration.serviceapi.exeption.InvalidFieldValuesException;

public interface RemoteCargoService {
	
	CargoDTO saveCargo(CargoDTO cargo) throws InvalidFieldValuesException;
	List<CargoDTO> getCargos();
	List<CargoDTO> getCargosByDate(Date date) throws InvalidDateException;
	
	CargoDTO assignCargoToCourier(Long cargoID, Long courierID) throws CargoAlreadyTakenException, CargoNotFoundException,
	CourierNotFoundException, BusyCourierException;

}
