package hu.schonherz.administration.serviceapi;

import java.util.List;

import hu.schonherz.administration.serviceapi.dto.UserDTO;

public interface RemoteUserService {

	public List<UserDTO> getUsers(String roleName);
	
}
