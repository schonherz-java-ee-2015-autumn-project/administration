package hu.schonherz.administration.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.administration.persistence.dao.CourierIncomeDao;
import hu.schonherz.administration.persistence.dao.UserDao;
import hu.schonherz.administration.persistence.dao.helper.CourierIncomeSpecification;
import hu.schonherz.administration.persistence.entities.CourierIncome;
import hu.schonherz.administration.persistence.entities.User;
import hu.schonherz.administration.service.converter.CourierIncomeConverter;
import hu.schonherz.administration.serviceapi.CourierIncomeService;
import hu.schonherz.administration.serviceapi.dto.CourierIncomeDTO;
import hu.schonherz.administration.serviceapi.dto.CustomSortOrder;
import hu.schonherz.administration.serviceapi.dto.SortMetaDTO;

@Stateless(mappedName = "CourierIncomeService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
@Local(CourierIncomeService.class)
public class CourierIncomeServiceImpl implements CourierIncomeService {

	@Autowired
	private CourierIncomeDao courierIncomeDao;

	@Autowired
	private UserDao userDao;

	private CourierIncomeConverter cv;

	@Override
	public List<CourierIncomeDTO> getAllCourierIncome() {
		return cv.toDTO(courierIncomeDao.findAll());

	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<CourierIncomeDTO> getCourierIncome(Long courierId) {
		User courier = userDao.findById(courierId);
		return cv.toDTO(courierIncomeDao.findByCourier(courier));
	}

	public CourierIncomeDao getCourierIncomeDao() {
		return courierIncomeDao;
	}

	public void setCourierIncomeDao(CourierIncomeDao courierIncomeDao) {
		this.courierIncomeDao = courierIncomeDao;
	}

	@PostConstruct
	void init() {
		cv = new CourierIncomeConverter();
		cv.setUserDao(userDao);
	}

	public CourierIncomeConverter getCv() {
		return cv;
	}

	public void setCv(CourierIncomeConverter cv) {
		this.cv = cv;
	}

	@Override
	public List<CourierIncomeDTO> getCourierIncome(int page, int pageSize, List<SortMetaDTO> sortMeta,
			Map<String, Object> filters) {
		Pageable p = createPageRequest(page, pageSize, sortMeta);
		return cv.toDTO(courierIncomeDao.findAll(buildSpecification(filters), p).getContent());
	}

	@Override
	public int getCourierIncomeCount(Map<String, Object> filters) {
		return (int) courierIncomeDao.count(buildSpecification(filters));
	}

	@Override
	public CourierIncomeDTO getCourierIncomeById(long id) {
		return cv.toDTO(courierIncomeDao.findOne(id));
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
		if (orders.isEmpty())
			return new PageRequest(first, pageSize, sort);
		else
			return new PageRequest(first, pageSize);

	}

	private Specification<CourierIncome> buildSpecification(Map<String, Object> filters) {
		Specification<CourierIncome> spec = null;
		String name;
		if (filters.containsKey("name")) {
			name = (String) filters.get("name");
			spec = Specifications.where(CourierIncomeSpecification.courierNameLike(name));
		}
		return spec;
	}

	@Override
	public void save(CourierIncomeDTO edited) {
		courierIncomeDao.save(cv.toEntity(edited));
	}

}
