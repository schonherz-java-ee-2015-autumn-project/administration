package hu.schonherz.administration.service.impl;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.administration.persistence.dao.RestaurantDao;
import hu.schonherz.administration.persistence.dao.helper.RestaurantSpecification;
import hu.schonherz.administration.persistence.entities.Restaurant;
import hu.schonherz.administration.service.converter.RestaurantConverter;
import hu.schonherz.administration.serviceapi.RestaurantService;
import hu.schonherz.administration.serviceapi.dto.CustomSortOrder;
import hu.schonherz.administration.serviceapi.dto.RestaurantDTO;

@Stateless(mappedName = "RestaurantService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
@Local(RestaurantService.class)
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	RestaurantDao restaurantDao;

	@Override
	public RestaurantDTO findRestaurantByName(String name) {
		Restaurant restaurant = restaurantDao.findByName(name);
		return RestaurantConverter.toDTO(restaurant);
	}

	@Override
	public RestaurantDTO findById(Long id) {
		Restaurant restaurant = restaurantDao.findOne(id);
		return RestaurantConverter.toDTO(restaurant);
	}

	@Override
	public void save(RestaurantDTO restaurantDTO) {
		Restaurant restaurant = RestaurantConverter.toEntity(restaurantDTO);
		restaurantDao.save(restaurant);
	}

	@Override
	public void remove(RestaurantDTO restaurantDTO) {
		restaurantDTO.setIsDeleted(true);
		save(restaurantDTO);
	}

	@Override
	public List<RestaurantDTO> getRestaurants() {
		List<Restaurant> restaurants = restaurantDao.findAll();
		return RestaurantConverter.toDTO(restaurants);
	}

	@Override
	public int getRestaurantCount() {
		return (int) restaurantDao.count();
	}

	@Override
	public int getRestaurantCount(Map<String, Object> filters) {
		return (int) restaurantDao
				.count(Specifications.where(buildSpecification(filters)).and(RestaurantSpecification.notDeleted()));
	}

	private Specification<Restaurant> buildSpecification(Map<String, Object> filters) {
		Specification<Restaurant> spec = null;
		String name;
		String address;
		String phoneNumber;
		String price;

		if (filters.containsKey("name")) {
			name = (String) filters.get("name");
			spec = Specifications.where(RestaurantSpecification.nameLike(name));
		}

		if (filters.containsKey("phoneNumber")) {
			phoneNumber = (String) filters.get("phoneNumber");
			spec = Specifications.where(spec).and(RestaurantSpecification.phoneNumberLike(phoneNumber));
		}

		if (filters.containsKey("address")) {
			address = (String) filters.get("address");
			spec = Specifications.where(spec).and(RestaurantSpecification.addressLike(address));
		}

		return spec;
	}

	@Override
	public List<RestaurantDTO> getRestaurants(int first, int pageSize, String sortField, CustomSortOrder sortOrder,
			Map<String, Object> filters) {
		Pageable pagable = createPageRequest(first, pageSize, sortField, sortOrder);
		Specification<Restaurant> spec = Specifications.where(buildSpecification(filters))
				.and(RestaurantSpecification.notDeleted());
		return RestaurantConverter.toDTO(restaurantDao.findAll(spec, pagable).getContent());
	}

	private Pageable createPageRequest(int first, int pageSize, String sortField, CustomSortOrder order) {
		if (order != null && sortField != null) {
			Sort sort = null;
			if (order.equals(order.DESC)) {
				sort = new Sort(Sort.Direction.DESC, sortField);
			} else {
				sort = new Sort(Sort.Direction.ASC, sortField);
			}
			return new PageRequest(first, pageSize, sort);
		} else {
			return new PageRequest(first, pageSize);

		}

	}
}
