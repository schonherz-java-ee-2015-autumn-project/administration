package hu.schonherz.administration.serviceapi;

import java.util.Date;
import java.util.List;

import hu.schonherz.administration.serviceapi.dto.UserDTO;
import hu.schonherz.administration.serviceapi.dto.UserRole;
import hu.schonherz.administration.serviceapi.exeption.NotAllowedRoleException;

public interface RemoteUserService {

	public List<UserDTO> getUsers(UserRole role) throws NotAllowedRoleException;

	public List<UserDTO> getUsers(UserRole role, Date lastModified) throws NotAllowedRoleException;

}
