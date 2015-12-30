package hu.schonherz.administration.service.impl;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.administration.persistence.dao.RestaurantDao;
import hu.schonherz.administration.persistence.dao.UserDao;
import hu.schonherz.administration.persistence.dao.helper.RestaurantSpecification;
import hu.schonherz.administration.persistence.entities.Restaurant;
import hu.schonherz.administration.persistence.entities.User;
import hu.schonherz.administration.service.converter.RestaurantConverter;
import hu.schonherz.administration.serviceapi.RemoteRestaurantService;
import hu.schonherz.administration.serviceapi.dto.RestaurantDTO;
import hu.schonherz.administration.serviceapi.dto.UserDTO;

@Stateless(mappedName = "RemoteRestaurantService")
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors(SpringBeanAutowiringInterceptor.class)
@Local(RemoteRestaurantService.class)
public class RemoteRestaurantServiceImpl implements RemoteRestaurantService {

	
	@Autowired
	RestaurantDao restaurantDao;
	

	@Autowired
	UserDao userDao;
	
	@Override
	public RestaurantDTO findById(Long id) {
		User user = userDao.findById(id);
		List<Restaurant> result = restaurantDao.findAll(Specifications.where(RestaurantSpecification.notDeleted()).and(RestaurantSpecification.hasUser(user)));
		if(result!=null && !result.isEmpty()){
			return RestaurantConverter.toDTO(result.get(0));
		}else{
			return null;
		}
	}

}
