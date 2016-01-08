package hu.schonherz.administration.serviceapi;

import java.util.List;
import java.util.Map;

import hu.schonherz.administration.serviceapi.dto.IncomeReportDTO;
import hu.schonherz.administration.serviceapi.dto.SortMetaDTO;

public interface IncomeReportService {

	List<IncomeReportDTO> getAllIncomeReports();
	
	List<IncomeReportDTO> getIncomeReports(int page, int pageSize, List<SortMetaDTO> sortMeta,
			Map<String, Object> filters);
	
	int getIncomeReportCount(Map<String, Object> filters);
	
	IncomeReportDTO getIncomeReportById(long id);

	IncomeReportDTO findById(long parseLong);
	
	void saveReport(IncomeReportDTO editReport);
}
