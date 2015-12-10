package hu.schonherz.administration.serviceapi;

import java.util.List;
import java.util.Map;

import hu.schonherz.administration.serviceapi.dto.CustomSortOrder;
import hu.schonherz.administration.serviceapi.dto.UserDTO;
import hu.schonherz.administration.serviceapi.dto.UserRole;

public interface UserService {

	public UserDTO findUserByName(String name) throws Exception;

	public UserDTO registrationUser(UserDTO UserDTO) throws Exception;

	public List<UserDTO> getUserList(int first, int pageSize, String sortField,  CustomSortOrder sortOrder,
			Map<String, Object> filters, UserRole role);

	public List<UserDTO> getUsers();
	
	public int getUserCount();
	
	public int getUserCount(Map<String, Object> filters);

	public UserDTO saveUser(UserDTO selectedUser);

	public UserDTO findById(Long id);

}
