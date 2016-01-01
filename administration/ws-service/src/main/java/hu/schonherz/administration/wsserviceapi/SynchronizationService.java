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
	public List<WebUserDTO> getUsers(@WebParam(name = "UserRole")UserRole role) throws NotAllowedRoleException;


	@WebMethod(operationName = "getUsersByRoleAndDate")
	@WebResult(name = "usersListResponse")
	public List<WebUserDTO> getUsersByDate(@WebParam(name = "UserRole")UserRole role,@WebParam(name = "LastModDate") Date lastModified) throws NotAllowedRoleException;
	
	
	@WebMethod(operationName = "findRestaurantByUserId")
	@WebResult(name = "restaurantResponse")
	public WebRestaurantDTO findRestaurantById(@WebParam(name = "UserId")Long id) throws NoRestaurantAssignedUserException;
	/**
	 * A szabad sz�ll�t�sokat adja vissza egy CargoVO t�pus� list�ban. CargoVO:
	 * Long id; UserVO user; RestaurantVO restaurant; List<AddressVO> addresses;
	 * CargoStatus status; double totalValue; Long globalid;
	 */
	// @WebMethod(operationName = "getFreeCargosList")
	// @WebResult(name = "freeCargosListResponse")
	// public List<CargoVO> getFreeCargos();

	/**
	 * Be�ll�tja a sz�ll�t�s st�tusz�t: CargoStatus(enum): Free(1L),
	 * Reserved(2L), Received(3L), Delivered(4L), Illetve visszadob egy
	 * "hibak�dot": Ha sikeres volt a m�velet (az adatb�zisba val�
	 * friss�t�s/ment�s) akkor 0, ha nem akkor 1.
	 * 
	 * @param globalId,cargoStatus
	 * @return
	 */

	// @WebMethod(operationName="setCargoStatus")
	// @WebResult(name="errorCode")
	// public Long setCargoStatus(@WebParam(name="globalId") Long
	// globalId,@WebParam(name="cargoStatus") CargoStatus cargoStatus);

}
