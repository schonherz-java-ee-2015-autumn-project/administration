package hu.schonherz.administration.web.report;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import hu.schonherz.administration.serviceapi.IncomeReportService;
import hu.schonherz.administration.serviceapi.dto.IncomeReportDTO;
import hu.schonherz.administration.serviceapi.dto.SortMetaDTO;
import hu.schonherz.administration.web.courier.converter.SortMetaConverter;

@Named
@EJB(name = "ejb.IncomeReportService", beanInterface = IncomeReportService.class)
public class LazyIncomeReport extends LazyDataModel<IncomeReportDTO> {

	private static final long serialVersionUID = 5066940969999731098L;

	@EJB
	IncomeReportService incomeReportService;

	@Override
	public IncomeReportDTO getRowData(String rowKey) {
		return incomeReportService.findById(Long.parseLong(rowKey));
	}

	@Override
	public Object getRowKey(IncomeReportDTO income) {
		return income.getId();
	}

	@Override
	public List<IncomeReportDTO> load(int first, int pageSize, List<SortMeta> sortMeta, Map<String, Object> filters) {
		List<SortMetaDTO> sortMetaDTO = SortMetaConverter.toDTO(sortMeta);
		int page = first / pageSize;
		if (incomeReportService != null) {
			List<IncomeReportDTO> list = incomeReportService.getIncomeReports(page, pageSize, sortMetaDTO, filters);
			int rowCount = incomeReportService.getIncomeReportCount(filters);
			if (list == null || list.isEmpty()) {
				return Collections.emptyList();
			} else {
				this.setRowCount(rowCount);
				return list;
			}
		} else {
			return Collections.emptyList();
		}
	}
}
