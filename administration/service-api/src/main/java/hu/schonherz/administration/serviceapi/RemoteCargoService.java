package hu.schonherz.administration.serviceapi;

import java.util.Date;
import java.util.List;

import hu.schonherz.administration.serviceapi.dto.CargoDTO;
import hu.schonherz.administration.serviceapi.dto.CargoState;
import hu.schonherz.administration.serviceapi.dto.PaymentMethod;
import hu.schonherz.administration.serviceapi.exeption.AddressNotFoundException;
import hu.schonherz.administration.serviceapi.exeption.BusyCourierException;
import hu.schonherz.administration.serviceapi.exeption.CargoAlreadyTakenException;
import hu.schonherz.administration.serviceapi.exeption.CargoNotFoundException;
import hu.schonherz.administration.serviceapi.exeption.CourierNotFoundException;
import hu.schonherz.administration.serviceapi.exeption.IllegalStateTransitionException;
import hu.schonherz.administration.serviceapi.exeption.InvalidDateException;
import hu.schonherz.administration.serviceapi.exeption.InvalidFieldValuesException;
import hu.schonherz.administration.serviceapi.exeption.ModifyWithoutIdException;
import hu.schonherz.administration.serviceapi.exeption.NotAllOrderCompletedException;
import hu.schonherz.administration.serviceapi.exeption.OrderException;
import hu.schonherz.administration.serviceapi.exeption.OrderNotFoundException;


public interface RemoteCargoService {

	CargoDTO saveCargo(CargoDTO cargo) throws InvalidFieldValuesException;
	
	CargoDTO modifyCargo(CargoDTO cargo) throws CargoNotFoundException, InvalidFieldValuesException, ModifyWithoutIdException, OrderNotFoundException;

	List<CargoDTO> getCargos();

	List<CargoDTO> getCargosByDate(Date date) throws InvalidDateException;

	CargoDTO getActiveCargoByCourier(long courierId) throws CourierNotFoundException, AddressNotFoundException;

	void assignCargoToCourier(Long cargoID, Long courierID) throws CargoAlreadyTakenException, CargoNotFoundException,
			CourierNotFoundException, BusyCourierException, InvalidFieldValuesException;

	void changeCargoState(long cargoId, long courierId, CargoState local)
			throws CargoNotFoundException, CargoAlreadyTakenException, IllegalStateTransitionException,
			CourierNotFoundException, NotAllOrderCompletedException, InvalidFieldValuesException;

	void changePaymentState(Long courierId, Long orderId, PaymentMethod paymentMethod)
			throws CourierNotFoundException, CargoNotFoundException, OrderException, AddressNotFoundException;

}
