package hu.schonherz.administration.wsservice.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jws.WebService;


import hu.schonherz.administration.serviceapi.*;
import hu.schonherz.administration.wsserviceapi.CourierWeb;
import hu.schonherz.administration.wsserviceapi.WebUserDTO;
import hu.schonherz.administration.wsserviceapi.converter.UserConverter;
@Stateless(mappedName = "courierWebImpl")
@WebService(endpointInterface = "hu.schonherz.administration.wsserviceapi.CourierWeb",
			serviceName="courierWebImpl")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Local(CourierWeb.class)
public class CourierWebImpl implements CourierWeb {

	@EJB(lookup = "java:global.ear.service-1.0.0.RemoteUserServiceImpl!hu.schonherz.administration.serviceapi.RemoteUserService")
	private RemoteUserService remoteUserService;
	@Override
	public List<WebUserDTO> getUsers() {
		return UserConverter.toWebUserDTO(remoteUserService.getUsers("eee"));		
		
	}

}
