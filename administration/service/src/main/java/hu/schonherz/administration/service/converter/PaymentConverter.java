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
		switch (payment) {
		case Cash:
			return PaymentMethod.Cash;

		case SZEPCard:
			return PaymentMethod.SZEPCard;

		case CreditCard:
			return PaymentMethod.CreditCard;

		case VOUCHER:
			return PaymentMethod.VOUCHER;

		}
		return null;
	}

	public static Payment toEntity(PaymentMethod paymentMethod) {
		if (paymentMethod == null) {
			return null;
		}
		switch (paymentMethod) {
		case Cash:
			return Payment.Cash;

		case SZEPCard:
			return Payment.SZEPCard;

		case CreditCard:
			return Payment.CreditCard;

		case VOUCHER:
			return Payment.VOUCHER;

		}
		return null;
	}

}
