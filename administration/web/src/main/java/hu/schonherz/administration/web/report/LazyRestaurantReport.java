package hu.schonherz.administration.web.report;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import hu.schonherz.administration.serviceapi.RestaurantReportService;
import hu.schonherz.administration.serviceapi.dto.RestaurantReportDTO;
import hu.schonherz.administration.serviceapi.dto.SortMetaDTO;
import hu.schonherz.administration.web.courier.converter.SortMetaConverter;

@Named
@EJB(name = "ejb.RestaurantReportService", beanInterface = RestaurantReportService.class)
public class LazyRestaurantReport extends LazyDataModel<RestaurantReportDTO> {

	private static final long serialVersionUID = 5066940969999731098L;
	
	@EJB
	private RestaurantReportService restaurantReportService;
	
	@Override
	public RestaurantReportDTO getRowData(String rowKey) {
		return restaurantReportService.findById(Long.parseLong(rowKey));
	}

	@Override
	public Object getRowKey(RestaurantReportDTO income) {
		return income.getId();
	}

	@Override
	public List<RestaurantReportDTO> load(int first, int pageSize, List<SortMeta> sortMeta, Map<String, Object> filters) {
		
			List<SortMetaDTO> sortMetaDTO = SortMetaConverter.toDTO(sortMeta);
			int page = first / pageSize;
			if (restaurantReportService != null) {
				List<RestaurantReportDTO> list = restaurantReportService.getRestaurantReports(page, pageSize, sortMetaDTO, filters);
				int rowCount = restaurantReportService.getRestaurantReportCount(filters);
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

	public RestaurantReportService getRestaurantReportService() {
		return restaurantReportService;
	}

	public void setRestaurantReportService(RestaurantReportService restaurantReportService) {
		this.restaurantReportService = restaurantReportService;
	}
}
