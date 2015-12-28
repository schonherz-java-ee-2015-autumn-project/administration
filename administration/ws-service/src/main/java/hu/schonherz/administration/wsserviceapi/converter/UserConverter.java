package hu.schonherz.administration.wsserviceapi.converter;

import java.util.ArrayList;
import java.util.List;

import hu.schonherz.administration.serviceapi.dto.UserDTO;
import hu.schonherz.administration.wsservice.dto.WebUserDTO;

public class UserConverter {

	public static List<WebUserDTO> toWebUserDTO(List<UserDTO> user) {
		List<WebUserDTO> rv = new ArrayList<>();
		for (UserDTO users : user) {
			rv.add(toWebUserDTO(users));
		}
		return rv;
	}

	public static WebUserDTO toWebUserDTO(UserDTO UserDTO) {
		WebUserDTO webUserDTO = new WebUserDTO();

		webUserDTO.setId(UserDTO.getId());
		webUserDTO.setName(UserDTO.getName());
		webUserDTO.setModdate(UserDTO.getModdate());
		webUserDTO.setPassword(UserDTO.getPassword());
		webUserDTO.setUsername(UserDTO.getUsername());
		webUserDTO.setRemove(UserDTO.isRemove());

		return webUserDTO;
	}
}
