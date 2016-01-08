package hu.schonherz.administration.web.courier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import hu.schonherz.administration.serviceapi.CourierIncomeService;
import hu.schonherz.administration.serviceapi.dto.CourierIncomeDTO;
import hu.schonherz.administration.serviceapi.dto.SortMetaDTO;
import hu.schonherz.administration.web.courier.converter.SortMetaConverter;

@Named
@ViewScoped
@EJB(name = "ejb.CourierIncomeService", beanInterface = CourierIncomeService.class)
public class LazyCourierIncome extends LazyDataModel<CourierIncomeDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6328443137790457376L;
	@EJB
	CourierIncomeService courierIncomeService;

	@Override
	public CourierIncomeDTO getRowData(String rowKey) {
		return courierIncomeService.getCourierIncomeById(Long.parseLong(rowKey));

	}

	@Override
	public Object getRowKey(CourierIncomeDTO income) {
		return income.getId();
	}

	@Override
	public List<CourierIncomeDTO> load(int first, int pageSize, List<SortMeta> sortMeta, Map<String, Object> filters) {

		List<SortMetaDTO> sortMetaDTO = SortMetaConverter.toDTO(sortMeta);
		int page = first / pageSize;

		if (courierIncomeService != null) {
			List<CourierIncomeDTO> list = courierIncomeService.getCourierIncome(page, pageSize, sortMetaDTO, filters);
			int rowCount = courierIncomeService.getCourierIncomeCount(filters);
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

	@Override
	public List<CourierIncomeDTO> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}

}