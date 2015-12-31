package hu.schonherz.administration.wsserviceapi.converter;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.schonherz.administration.serviceapi.dto.DeliveryStateServ;
import hu.schonherz.administration.wsservice.dto.DeliveryStateWeb;

public class RemoteDeliveryStateConverter {
	static Mapper mapper = new DozerBeanMapper();

	public static DeliveryStateWeb toRemote(DeliveryStateServ state) {
		if (state == null) {
			return null;
		}
		switch (state) {
		case Delivered:
			return DeliveryStateWeb.Delivered;
		case Failed:
			return DeliveryStateWeb.Failed;
		case In_progress:
			return DeliveryStateWeb.In_progress;
		}
		return null;
	}

	public static DeliveryStateServ toLocal(DeliveryStateWeb state) {
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

}
