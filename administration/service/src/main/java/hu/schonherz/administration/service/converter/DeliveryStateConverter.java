package hu.schonherz.administration.service.converter;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.schonherz.administration.persistence.entities.helper.DeliveryState;
import hu.schonherz.administration.persistence.entities.helper.State;
import hu.schonherz.administration.serviceapi.dto.CargoState;
import hu.schonherz.administration.serviceapi.dto.DeliveryStateServ;

public class DeliveryStateConverter {
	static Mapper mapper = new DozerBeanMapper();

	public static DeliveryStateServ toDTO(DeliveryState state) {
		if (state == null) {
			return null;
		}
		switch (state) {
		case Delivered:
			return DeliveryStateServ.Delivered;
		case Failed:
			return DeliveryStateServ.Failed;
		case In_progress:
			return DeliveryStateServ.In_progress;
		}
		return null;
	}

	public static DeliveryState toEntity(DeliveryStateServ state) {
		if (state == null) {
			return null;
		}
		switch (state) {
		case Delivered:
			return DeliveryState.Delivered;
		case Failed:
			return DeliveryState.Failed;
		case In_progress:
			return DeliveryState.In_progress;
		}
		return null;

	}

}
