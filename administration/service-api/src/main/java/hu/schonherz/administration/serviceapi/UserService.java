package hu.schonherz.administration.serviceapi;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import hu.schonherz.administration.serviceapi.dto.UserDTO;


public interface UserService {

	public UserDTO findUserByName(String name) throws Exception;

	public UserDTO registrationUser(UserDTO UserDTO) throws Exception;

	public List<UserDTO> getUserList(int first, int pageSize, String sortField,  SortOrder sortOrder,
			Map<String, Object> filters);

	public List<UserDTO> getUsers();
	
	public Integer getUserCount();

	public UserDTO saveUser(UserDTO selectedUser);

	public UserDTO findById(Long id);

}
