package hu.schonherz.administration.service.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.administration.persistence.dao.RoleDao;
import hu.schonherz.administration.persistence.dao.UserDao;
import hu.schonherz.administration.persistence.dao.helper.RestaurantSpecification;
import hu.schonherz.administration.persistence.dao.helper.UserSpecification;
import hu.schonherz.administration.persistence.entities.Role;
import hu.schonherz.administration.persistence.entities.User;
import hu.schonherz.administration.service.converter.UserConverter;
import hu.schonherz.administration.serviceapi.RemoteUserService;
import hu.schonherz.administration.serviceapi.dto.UserDTO;
import hu.schonherz.administration.serviceapi.dto.UserRole;
import hu.schonherz.administration.serviceapi.exeption.NotAllowedRoleException;

@Stateless(mappedName = "RemoteUserService")
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors(SpringBeanAutowiringInterceptor.class)
@Local(RemoteUserService.class)
public class RemoteUserServiceImpl implements RemoteUserService {

	@Autowired
	UserDao userDao;

	@Autowired
	RoleDao roleDao;

	@Override
	public List<UserDTO> getUsers(UserRole role) throws NotAllowedRoleException {

		if (role.equals(UserRole.ADMIN)) {
			throw new NotAllowedRoleException();
		}
		Specification<User> roleSpec = buildRoleSpecification(role);
		return UserConverter.toVo(userDao.findAll(roleSpec));
	}

	private Specification<User> buildRoleSpecification(UserRole role) {
		Specification<User> spec = null;
		Role roleEntity;
		switch (role) {
		case COURIER:
			roleEntity = roleDao.findByName("ROLE_COURIER");
			spec = Specifications.where(UserSpecification.hasRole(roleEntity));
			break;
		case RESTAURANT:
			roleEntity = roleDao.findByName("ROLE_RESTAURANT");
			spec = Specifications.where(UserSpecification.hasRole(roleEntity));
			break;
		default:
			break;
		}
		return spec;
	}

	@Override
	public List<UserDTO> getUsers(UserRole role, Date lastModified) throws NotAllowedRoleException {
		if(role.equals(UserRole.ADMIN))
			throw new NotAllowedRoleException();
		Specification<User> spec = Specifications.where(buildRoleSpecification(role))
				.and(UserSpecification.lastModifiedAt(lastModified));
		
		return UserConverter.toVo(userDao.findAll(spec));
	}
}
