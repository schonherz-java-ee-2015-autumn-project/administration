package hu.schonherz.administration.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.administration.persistence.dao.RestaurantReportDao;
import hu.schonherz.administration.persistence.dao.helper.RestaurantReportSpecification;
import hu.schonherz.administration.persistence.entities.RestaurantReport;
import hu.schonherz.administration.service.converter.RestaurantReportConverter;
import hu.schonherz.administration.serviceapi.RestaurantReportService;
import hu.schonherz.administration.serviceapi.dto.CustomSortOrder;
import hu.schonherz.administration.serviceapi.dto.RestaurantReportDTO;
import hu.schonherz.administration.serviceapi.dto.SortMetaDTO;

@Stateless(mappedName = "RestaurantReportService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
@Local(RestaurantReportService.class)
public class RestaurantReportServiceImpl implements RestaurantReportService {

	@Autowired
	RestaurantReportDao restaurantReportDao;

	@Override
	public List<RestaurantReportDTO> getRestaurantReports(int page, int pageSize, List<SortMetaDTO> sortMeta,
			Map<String, Object> filters) {
		Pageable p = createPageRequest(page, pageSize, sortMeta);
		return RestaurantReportConverter
				.toDTOList(restaurantReportDao.findAll(buildSpecification(filters), p).getContent());
	}

	@Override
	public int getRestaurantReportCount(Map<String, Object> filters) {
		return (int) restaurantReportDao.count(buildSpecification(filters));
	}

	@Override
	public RestaurantReportDTO findById(long parseLong) {

		return RestaurantReportConverter.toDTO(restaurantReportDao.findOne(parseLong));
	}

	@Override
	public void saveReport(RestaurantReportDTO editReport) {
		restaurantReportDao.save(RestaurantReportConverter.toEntity(editReport));
	}

	private Pageable createPageRequest(int first, int pageSize, List<SortMetaDTO> sortMeta) {
		List<Order> orders = new ArrayList<>();
		if (sortMeta == null)
			return new PageRequest(first, pageSize);
		for (SortMetaDTO sm : sortMeta) {
			if (sm.getOrder().equals(CustomSortOrder.DESC))
				orders.add(new Order(Sort.Direction.DESC, sm.getColumnName()));
			else
				orders.add(new Order(Sort.Direction.ASC, sm.getColumnName()));
		}
		Sort sort = new Sort(orders);
		if (!orders.isEmpty())
			return new PageRequest(first, pageSize, sort);
		else
			return new PageRequest(first, pageSize);
	}

	private Specification<RestaurantReport> buildSpecification(Map<String, Object> filters) {
		Specification<RestaurantReport> spec = null;
		String name;
		if (filters.containsKey("restaurant")) {
			name = (String) filters.get("restaurant");
			spec = Specifications.where(spec).and(RestaurantReportSpecification.courierNameLike(name));
		}
		if (filters.containsKey("date")) {
			String date = (String) filters.get("date");
			String[] components = date.split("-");
			int year = 0;
			int month = 0;
			int day = 0;
			try {
				if (components.length > 2) {
					year = Integer.parseInt(components[0]);
					month = Integer.parseInt(components[1]);
					day = Integer.parseInt(components[2]);
				}
				LocalDate localDate = new LocalDate(year, month, day);
				spec = Specifications.where(spec).and(RestaurantReportSpecification.lastModifiedAt(localDate.toDate()));
			} catch (Exception e) {

			}
		}
		return spec;
	}
}
