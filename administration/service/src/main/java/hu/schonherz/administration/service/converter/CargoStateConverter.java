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
		switch(state){
		case Delivered: return CargoState.Delivered;
		case Delivering: return CargoState.Delivering;
		case Free: return CargoState.Free;
		case Taken: return CargoState.Taken;
		}
		return null;
	}

	public static State toEntity(CargoState CargoState) {
		if (CargoState == null) {
			return null;
		}
		switch(CargoState){
		case Delivered: return State.Delivered;
		case Delivering: return State.Delivering;
		case Free: return State.Free;
		case Taken: return State.Taken;
		}
		return null;

	}


}
