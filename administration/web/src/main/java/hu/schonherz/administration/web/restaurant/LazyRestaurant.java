package hu.schonherz.administration.web.restaurant;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import hu.schonherz.administration.serviceapi.RestaurantService;
import hu.schonherz.administration.serviceapi.dto.CustomSortOrder;
import hu.schonherz.administration.serviceapi.dto.RestaurantDTO;

@Named
@EJB(name = "ejb.RestaurantService", beanInterface = RestaurantService.class)
public class LazyRestaurant extends LazyDataModel<RestaurantDTO> {

	@EJB
	RestaurantService restaurantService;

	@Override
	public RestaurantDTO getRowData(String rowKey) {
		Long id = Long.parseLong(rowKey);
		return restaurantService.findById(id);
		}

	@Override
	public Object getRowKey(RestaurantDTO restaurantDTO) {
		return restaurantDTO.getId();
	}

	@Override
	public List<RestaurantDTO> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {

		CustomSortOrder order;
		if (sortOrder.equals(SortOrder.DESCENDING))
			order = CustomSortOrder.DESC;
		else
			order = CustomSortOrder.ASC;

		int page = first / pageSize;

		if (restaurantService != null) {
			List<RestaurantDTO> list = restaurantService.getRestaurants(page, pageSize, sortField, order, filters);
			int rowCount = restaurantService.getRestaurantCount(filters);

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