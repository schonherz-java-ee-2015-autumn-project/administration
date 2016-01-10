package hu.schonherz.administration.wsserviceapi.converter;

import java.util.ArrayList;
import java.util.List;

import hu.schonherz.administration.serviceapi.dto.CargoDTO;
import hu.schonherz.administration.wsservice.dto.RemoteCargoDTO;

public class RemoteCargoConverter {

	public static CargoDTO toDTO(RemoteCargoDTO cargo) {
		if (cargo == null) {
			return null;
		}
		CargoDTO result = new CargoDTO();
		result.setCourierId(cargo.getCourierId());
		result.setId(cargo.getId());
		result.setOrders(RemoteOrderConverter.toDTO(cargo.getOrders()));
		result.setRestaurantId(cargo.getRestaurantId());
		result.setState(RemoteCargoStateConverter.toLocal(cargo.getState()));
		result.setIsDeleted(cargo.getIsDeleted());
		return result;
	}

	public static RemoteCargoDTO toRemoteDTO(CargoDTO cargoDTO) {
		if (cargoDTO == null) {
			return null;
		}
		RemoteCargoDTO result = new RemoteCargoDTO();
		result.setCourierId(cargoDTO.getCourierId());
		result.setId(cargoDTO.getId());
		result.setCourierName(cargoDTO.getCourierName());
		result.setOrders(RemoteOrderConverter.toRemoteDTO(cargoDTO.getOrders()));
		result.setRestaurantId(cargoDTO.getRestaurantId());
		result.setState(RemoteCargoStateConverter.toRemote(cargoDTO.getState()));
		result.setIsDeleted(cargoDTO.getIsDeleted());
		return result;

	}

	public static List<RemoteCargoDTO> toRemoteDTO(List<CargoDTO> cargos) {
		List<RemoteCargoDTO> rv = new ArrayList<>();
		for (CargoDTO cargo : cargos) {
			rv.add(toRemoteDTO(cargo));
		}
		return rv;
	}

	public static List<CargoDTO> toDTO(List<RemoteCargoDTO> cargos) {
		List<CargoDTO> rv = new ArrayList<>();
		for (RemoteCargoDTO cargo : cargos) {
			rv.add(toDTO(cargo));
		}
		return rv;
	}

}
