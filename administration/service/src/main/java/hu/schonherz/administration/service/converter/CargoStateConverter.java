package hu.schonherz.administration.service.converter;

import hu.schonherz.administration.persistence.entities.helper.State;
import hu.schonherz.administration.serviceapi.dto.CargoState;

public class CargoStateConverter {

	public CargoState toDTO(State state) {
		return CargoState.valueOf(state.name());
	}

	public State toEntity(CargoState CargoState) {
		return State.valueOf(CargoState.name());
	}
}
