package hu.schonherz.administration.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.administration.persistence.dao.CourierIncomeDao;
import hu.schonherz.administration.persistence.dao.UserDao;
import hu.schonherz.administration.persistence.entities.User;
import hu.schonherz.administration.service.converter.CourierIncomeConverter;
import hu.schonherz.administration.serviceapi.CourierIncomeService;
import hu.schonherz.administration.serviceapi.dto.CourierIncomeDTO;


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
	void init(){
		cv = new CourierIncomeConverter();
		cv.setUserDao(userDao);
	}

	public CourierIncomeConverter getCv() {
		return cv;
	}

	public void setCv(CourierIncomeConverter cv) {
		this.cv = cv;
	}
	
	

}
