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
import hu.schonherz.administration.persistence.entities.Order;
import hu.schonherz.administration.service.converter.OrderConverter;
import hu.schonherz.administration.serviceapi.RemoteOrderService;
import hu.schonherz.administration.serviceapi.dto.OrderDTO;

@Stateless(mappedName = "RemoteOrderService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
@Local(RemoteOrderService.class)
public class RemoteOrderServiceImpl implements RemoteOrderService {

	@Autowired
	private OrderDao orderDao;
	
	@Override
	public OrderDTO saveOrder(OrderDTO order) {
		Order orderEntity = OrderConverter.toEntity(order);
		
		return OrderConverter.toDTO(orderDao.save(orderEntity));
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
