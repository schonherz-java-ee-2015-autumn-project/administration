package hu.schonherz.administration.service.impl;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.administration.persistence.dao.ItemQuantityDao;
import hu.schonherz.administration.service.converter.ItemQuantityConverter;
import hu.schonherz.administration.serviceapi.RemoteItemQuantityService;
import hu.schonherz.administration.serviceapi.dto.ItemQuantityDTO;

@Stateless(mappedName = "RemoteItemQuantityService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
@Local(RemoteItemQuantityService.class)
public class RemoteItemQuantityServiceImpl implements RemoteItemQuantityService {

	@Autowired
	private ItemQuantityDao iqDao;

	@Override
	public ItemQuantityDTO saveItemQuantity(ItemQuantityDTO item) {
		return ItemQuantityConverter.toDTO(iqDao.save(ItemQuantityConverter.toEntity(item)));
	}

	@Override
	public List<ItemQuantityDTO> getItemQuantities() {
		return ItemQuantityConverter.toDTO(iqDao.findAll());
	}

	@Override
	public ItemQuantityDTO findById(Long id) {
		return ItemQuantityConverter.toDTO(iqDao.findOne(id));
	}

}
