package hu.schonherz.administration.service.converter;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.schonherz.administration.persistence.entities.helper.State;
import hu.schonherz.administration.serviceapi.dto.CargoState;

public class CargoStateConverter {
	static Mapper mapper = new DozerBeanMapper();

	public static CargoState toDTO(State state) {
		if (state == null) {
			return null;
		}
		return mapper.map(state, CargoState.class);
	}

	public static State toEntity(CargoState CargoState) {
		if (CargoState == null) {
			return null;
		}
		return mapper.map(CargoState, State.class);
	}


}
