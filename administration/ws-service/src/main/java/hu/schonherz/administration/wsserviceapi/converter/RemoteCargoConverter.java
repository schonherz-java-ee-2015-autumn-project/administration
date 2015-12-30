package hu.schonherz.administration.wsserviceapi.converter;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.schonherz.administration.serviceapi.dto.CargoDTO;
import hu.schonherz.administration.wsservice.dto.RemoteCargoDTO;

public class RemoteCargoConverter {

	static Mapper mapper = new DozerBeanMapper();

	public static CargoDTO toDTO(RemoteCargoDTO cargo) {
		if (cargo == null) {
			return null;
		}
		return mapper.map(cargo, CargoDTO.class);
	}

	public static RemoteCargoDTO toRemoteDTO(CargoDTO CargoDTO) {
		if (CargoDTO == null) {
			return null;
		}
		return mapper.map(CargoDTO, RemoteCargoDTO.class);
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
