package hu.schonherz.administration.wsservice.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.jws.WebService;

import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.administration.serviceapi.RemoteRestaurantService;
import hu.schonherz.administration.serviceapi.RemoteUserService;
import hu.schonherz.administration.serviceapi.dto.UserRole;
import hu.schonherz.administration.serviceapi.exeption.NoRestaurantAssignedUserException;
import hu.schonherz.administration.serviceapi.exeption.NotAllowedRoleException;
import hu.schonherz.administration.wsservice.dto.WebRestaurantDTO;
import hu.schonherz.administration.wsservice.dto.WebUserDTO;
import hu.schonherz.administration.wsserviceapi.SynchronizationService;
import hu.schonherz.administration.wsserviceapi.converter.RestaurantConverter;
import hu.schonherz.administration.wsserviceapi.converter.UserConverter;

@Stateless(mappedName = "SynchronizationServiceImpl")
@WebService(endpointInterface = "hu.schonherz.administration.wsserviceapi.SynchronizationService", serviceName = "SynchronizationServiceImpl")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class SynchronizationServiceImpl implements SynchronizationService {

	@EJB
	private RemoteUserService remoteUserService;
	@EJB
	private RemoteRestaurantService RemoteRestaurantService;
	
	@Override
	public List<WebUserDTO> getUsers(UserRole role) throws NotAllowedRoleException {
		return UserConverter.toWebUserDTO(remoteUserService.getUsers(role));
	}


	@Override
	public List<WebUserDTO> getUsersByDate(UserRole role, Date lastModified) throws NotAllowedRoleException {
		return UserConverter.toWebUserDTO(remoteUserService.getUsers(role, lastModified));
	}


	@Override
	public WebRestaurantDTO findRestaurantById(Long id) throws NoRestaurantAssignedUserException{
		return RestaurantConverter.toWebRestaurantDTO(RemoteRestaurantService.findById(id));
	}

}
