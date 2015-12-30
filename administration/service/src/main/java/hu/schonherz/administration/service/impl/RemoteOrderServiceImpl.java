package hu.schonherz.administration.service.impl;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.administration.persistence.dao.OrderDao;
import hu.schonherz.administration.service.converter.OrderConverter;
import hu.schonherz.administration.serviceapi.RemoteOrderService;
import hu.schonherz.administration.serviceapi.RemoteUserService;
import hu.schonherz.administration.serviceapi.dto.OrderDTO;

@Stateless(mappedName = "RemoteOrderService")
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors(SpringBeanAutowiringInterceptor.class)
@Local(RemoteUserService.class)
public class RemoteOrderServiceImpl implements RemoteOrderService {

	@Autowired
	private OrderDao orderDao;
	
	@Override
	public void saveOrder(OrderDTO order) {
		orderDao.save(OrderConverter.toEntity(order));
	}

	@Override
	public List<OrderDTO> getOrders() {
		return OrderConverter.toDTO(orderDao.findAll());
	}

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	


}
