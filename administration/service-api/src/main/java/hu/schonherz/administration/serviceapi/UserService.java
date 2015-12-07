package hu.schonherz.administration.serviceapi;

import java.util.List;

import hu.schonherz.administration.serviceapi.dto.UserDTO;


public interface UserService {

	public UserDTO findUserByName(String name) throws Exception;

	public UserDTO registrationUser(UserDTO UserDTO) throws Exception;

	public List<UserDTO> getUserList(int i, int pageSize, String sortField, int dir, String filter,
			String filterColumnName);

	public List<UserDTO> getUsers();
	
	public Integer getUserCount();

	public UserDTO saveUser(UserDTO selectedUser);

	public UserDTO findById(Long id);

}
