package hu.schonherz.administration.wsserviceapi.converter;

import hu.schonherz.administration.serviceapi.dto.CargoState;
import hu.schonherz.administration.wsservice.dto.RemoteCargoState;

public class RemoteCargoStateConverter {

	public static RemoteCargoState toRemote(CargoState state) {
		if (state == null) {
			return null;
		}
		switch(state){
		case Delivered: return RemoteCargoState.Delivered;
		case Delivering: return RemoteCargoState.Delivering;
		case Free: return RemoteCargoState.Free;
		case Taken: return RemoteCargoState.Taken;
		}
		return null;
	}

	public static CargoState toLocal(RemoteCargoState cargoState) {
		if (cargoState == null) {
			return null;
		}
		switch(cargoState){
		case Delivered: return CargoState.Delivered;
		case Delivering: return CargoState.Delivering;
		case Free: return CargoState.Free;
		case Taken: return CargoState.Taken;
		}
		return null;

	}


}
