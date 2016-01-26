package hu.schonherz.administration.service.converter;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.schonherz.administration.persistence.entities.helper.DeliveryState;
import hu.schonherz.administration.serviceapi.dto.DeliveryStateServ;

public class DeliveryStateConverter {
	static Mapper mapper = new DozerBeanMapper();

	public static DeliveryStateServ toDTO(DeliveryState state) {
		return DeliveryStateServ.valueOf(state.name());
	}

	public static DeliveryState toEntity(DeliveryStateServ state) {
		return DeliveryState.valueOf(state.name());
	}

}
