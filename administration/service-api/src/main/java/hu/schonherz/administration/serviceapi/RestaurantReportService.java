package hu.schonherz.administration.serviceapi;

import java.util.List;
import java.util.Map;

import hu.schonherz.administration.serviceapi.dto.RestaurantReportDTO;
import hu.schonherz.administration.serviceapi.dto.SortMetaDTO;

public interface RestaurantReportService {

	List<RestaurantReportDTO> getRestaurantReports(int page, int pageSize, List<SortMetaDTO> sortMeta,
			Map<String, Object> filters);
	
	int getRestaurantReportCount(Map<String, Object> filters);

	RestaurantReportDTO findById(long parseLong);
	
	void saveReport(RestaurantReportDTO editReport);
}
