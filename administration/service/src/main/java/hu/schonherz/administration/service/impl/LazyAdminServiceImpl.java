package hu.schonherz.administration.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.Entity;

import org.apache.log4j.Logger;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.administration.persistence.dao.UserDao;
import hu.schonherz.administration.persistence.dao.helper.UserSpecification;
import hu.schonherz.administration.persistence.entities.User;
import hu.schonherz.administration.service.converter.UserConverter;
import hu.schonherz.administration.serviceapi.LazyAdminService;
import hu.schonherz.administration.serviceapi.dto.UserDTO;

@Stateless(mappedName = "LazyAdminService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
@Local(LazyAdminService.class)
public class LazyAdminServiceImpl implements LazyAdminService  {

	@Autowired
	private UserDao userDAO;

	@Override
	public UserDTO getRowData(String rowKey) {
		User user = userDAO.getOne(Long.getLong(rowKey));
		return UserConverter.toVo(user);

	}

	@Override
	public Object getRowKey(UserDTO userDTO) {
		User user = userDAO.findByUsername(userDTO.getUsername());
		return UserConverter.toVo(user);
	}

	@Override
	public List<UserDTO> load(int first, int pageSize, String sortField,  SortOrder sortOrder,
			Map<String, Object> filters) {
		
		String order;
		switch ( sortOrder){
		case ASCENDING: order = "asc"; break;
		case DESCENDING: order = "desc"; break;
		case UNSORTED: order = "asc"; break;
		}
	
		

		userDAO.findAll(UserSpecification.nameLike("a"));
		return null;
	}

}