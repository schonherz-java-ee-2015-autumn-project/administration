package hu.schonherz.administration.service.converter;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.schonherz.administration.persistence.entities.helper.Payment;
import hu.schonherz.administration.serviceapi.dto.PaymentMethod;

public class PaymentConverter {
	static Mapper mapper = new DozerBeanMapper();

	public static PaymentMethod toDTO(Payment payment) {
		if (payment == null) {
			return null;
		}
		return mapper.map(payment, PaymentMethod.class);
	}

	public static Payment toEntity(PaymentMethod paymentMethod) {
		if (paymentMethod == null) {
			return null;
		}
		return mapper.map(paymentMethod, Payment.class);
	}

}
