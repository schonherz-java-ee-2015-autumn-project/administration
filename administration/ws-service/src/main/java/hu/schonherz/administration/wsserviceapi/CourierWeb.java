package hu.schonherz.administration.wsserviceapi;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;


@WebService
public interface CourierWeb {
	
	/**
	 * A felhasz�ln�l�kat adja vissza az adatb�zisb�l amit ut�na egy UserVO list�ban dob �t a Courier Modul-ra.
	 * UserVO:
	 *  Long id;
		String fullname;
		String username;
		String password;
		Long transporting;
		List<RoleVO> roles; 
		Long globalid;
		Date regdate;
	    Date moddate;
	 */
	
	@WebMethod(operationName = "getUsersList")
	@WebResult(name = "usersListResponse")
	public List<WebUserDTO> getUsers();
	
	/**
	 * A szabad sz�ll�t�sokat adja vissza egy CargoVO t�pus� list�ban.
	 * CargoVO:
	 * Long id;
	   UserVO user;
	   RestaurantVO restaurant;
 	   List<AddressVO> addresses;
	   CargoStatus status;
	   double totalValue;
	   Long globalid;
	 */
//	@WebMethod(operationName = "getFreeCargosList")
//	@WebResult(name = "freeCargosListResponse")
//	public List<CargoVO> getFreeCargos();
	
	/**
	 * Be�ll�tja a sz�ll�t�s st�tusz�t: CargoStatus(enum): Free(1L), Reserved(2L), Received(3L), Delivered(4L),
	 * Illetve visszadob egy "hibak�dot": Ha sikeres volt a m�velet (az adatb�zisba val� friss�t�s/ment�s) akkor 0, ha nem akkor 1.
	 * @param globalId,cargoStatus
	 * @return
	 */
	
//	@WebMethod(operationName="setCargoStatus")
//	@WebResult(name="errorCode")
//	public Long setCargoStatus(@WebParam(name="globalId") Long globalId,@WebParam(name="cargoStatus") CargoStatus cargoStatus);

}
