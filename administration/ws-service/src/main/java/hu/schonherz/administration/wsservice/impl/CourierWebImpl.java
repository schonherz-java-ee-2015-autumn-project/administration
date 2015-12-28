package hu.schonherz.administration.wsservice.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.jws.WebService;

import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.administration.serviceapi.RemoteUserService;
import hu.schonherz.administration.serviceapi.dto.UserRole;
import hu.schonherz.administration.wsservice.dto.WebUserDTO;
import hu.schonherz.administration.wsserviceapi.CourierWeb;
import hu.schonherz.administration.wsserviceapi.converter.UserConverter;

@Stateless(mappedName = "courierWebImpl")
@WebService(endpointInterface = "hu.schonherz.administration.wsserviceapi.CourierWeb", serviceName = "courierWebImpl")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class CourierWebImpl implements CourierWeb {

	@EJB
	private RemoteUserService remoteUserService;

	@Override
	public List<WebUserDTO> getUsers(UserRole role) {
		return UserConverter.toWebUserDTO(remoteUserService.getUsers(role));
	}

}
