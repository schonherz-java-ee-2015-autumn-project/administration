package hu.schonherz.administration.serviceapi;

import java.util.List;
import java.util.Map;

import hu.schonherz.administration.serviceapi.dto.CourierIncomeDTO;
import hu.schonherz.administration.serviceapi.dto.SortMetaDTO;

public interface CourierIncomeService {

	List<CourierIncomeDTO> getAllCourierIncome();

	List<CourierIncomeDTO> getCourierIncome(Long courierId);

	List<CourierIncomeDTO> getCourierIncome(int page, int pageSize, List<SortMetaDTO> sortMeta, Map<String, Object> filters);

	int getCourierIncomeCount(Map<String, Object> filters);

	CourierIncomeDTO getCourierIncomeById(long parseLong);

}
