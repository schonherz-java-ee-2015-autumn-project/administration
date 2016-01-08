package hu.schonherz.administration.service.impl;

import java.util.ArrayList;
import java.util.Date;
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

import hu.schonherz.administration.persistence.dao.IncomeReportDao;
import hu.schonherz.administration.persistence.dao.helper.CargoSpecification;
import hu.schonherz.administration.persistence.dao.helper.IncomeReportSpecification;
import hu.schonherz.administration.persistence.entities.Cargo;
import hu.schonherz.administration.persistence.entities.IncomeReport;
import hu.schonherz.administration.service.converter.IncomeReportConverter;
import hu.schonherz.administration.serviceapi.IncomeReportService;
import hu.schonherz.administration.serviceapi.dto.CustomSortOrder;
import hu.schonherz.administration.serviceapi.dto.IncomeReportDTO;
import hu.schonherz.administration.serviceapi.dto.SortMetaDTO;

@Stateless(mappedName = "IncomeReportService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
@Local(IncomeReportService.class)
public class IncomeReportServiceImpl implements IncomeReportService {

	@Autowired
	private IncomeReportDao incomeReportDao;

	@Override
	public List<IncomeReportDTO> getAllIncomeReports() {
		return IncomeReportConverter.toDTO(incomeReportDao.findAll());
	}

	@Override
	public List<IncomeReportDTO> getIncomeReports(int page, int pageSize, List<SortMetaDTO> sortMeta,
			Map<String, Object> filters) {
		Pageable p = createPageRequest(page, pageSize, sortMeta);
		return IncomeReportConverter.toDTO(incomeReportDao.findAll(buildSpecification(filters), p).getContent());
		
	}

	@Override
	public int getIncomeReportCount(Map<String, Object> filters) {
		return (int) incomeReportDao.count(buildSpecification(filters));
	}

	@Override
	public IncomeReportDTO getIncomeReportById(long id) {
		return IncomeReportConverter.toDTO(incomeReportDao.findOne(id));
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

	private Specification<IncomeReport> buildSpecification(Map<String, Object> filters) {
		Specification<IncomeReport> spec = null;

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
				spec = Specifications.where(spec).and(IncomeReportSpecification.lastModifiedAt(localDate.toDate()));
			} catch (Exception e) {

			}
		}
		return spec;
	}

	@Override
	public IncomeReportDTO findById(long parseLong) {
		return IncomeReportConverter.toDTO(incomeReportDao.findOne(parseLong));
	}

	@Override
	public void saveReport(IncomeReportDTO editReport) {
		incomeReportDao.save(IncomeReportConverter.toEntity(editReport));
	}
}
