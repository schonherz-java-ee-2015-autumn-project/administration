package hu.schonherz.administration.serviceapi;

import java.util.List;

import hu.schonherz.administration.serviceapi.dto.CourierIncomeDTO;

public interface CourierIncomeService {
	
	List<CourierIncomeDTO> getAllCourierIncome();

	List<CourierIncomeDTO> getCourierIncome(Long courierId);
}
