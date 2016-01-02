package hu.schonherz.administration.wsserviceapi;

import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import hu.schonherz.administration.serviceapi.dto.UserRole;
import hu.schonherz.administration.serviceapi.exeption.NoRestaurantAssignedUserException;
import hu.schonherz.administration.serviceapi.exeption.NotAllowedRoleException;
import hu.schonherz.administration.wsservice.dto.WebRestaurantDTO;
import hu.schonherz.administration.wsservice.dto.WebUserDTO;

@WebService
public interface SynchronizationService {

	@WebMethod(operationName = "getUsersByRole")
	@WebResult(name = "usersListResponse")
	public List<WebUserDTO> getUsers(@WebParam(name = "UserRole") UserRole role) throws NotAllowedRoleException;

	@WebMethod(operationName = "getUsersByRoleAndDate")
	@WebResult(name = "usersListResponse")
	public List<WebUserDTO> getUsersByDate(@WebParam(name = "UserRole") UserRole role,
			@WebParam(name = "LastModDate") Date lastModified) throws NotAllowedRoleException;

	@WebMethod(operationName = "findRestaurantByUserId")
	@WebResult(name = "restaurantResponse")
	public WebRestaurantDTO findRestaurantById(@WebParam(name = "UserId") Long id)
			throws NoRestaurantAssignedUserException;
}
