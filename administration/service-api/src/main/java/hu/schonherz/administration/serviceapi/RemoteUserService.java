package hu.schonherz.administration.serviceapi;

import java.util.List;

import hu.schonherz.administration.serviceapi.dto.UserDTO;
import hu.schonherz.administration.serviceapi.dto.UserRole;

public interface RemoteUserService {

	public List<UserDTO> getUsers(UserRole role);

}
