package hu.schonherz.administration.service.impl;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.administration.persistence.dao.ItemDao;
import hu.schonherz.administration.service.converter.ItemConverter;
import hu.schonherz.administration.serviceapi.RemoteItemService;
import hu.schonherz.administration.serviceapi.RemoteUserService;
import hu.schonherz.administration.serviceapi.dto.ItemDTO;

@Stateless(mappedName = "RemoteItemService")
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors(SpringBeanAutowiringInterceptor.class)
@Local(RemoteUserService.class)
public class RemoteItemServiceImpl implements RemoteItemService {

	@Autowired
	private ItemDao itemDao;
	
	@Override
	public void saveItem(ItemDTO item) {
		itemDao.save(ItemConverter.toEntity(item));
	}

	@Override
	public List<ItemDTO> getItems() {
		return ItemConverter.toDTO(itemDao.findAll());
	}

	public ItemDao getItemDao() {
		return itemDao;
	}

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

}
