package hu.schonherz.administration.service.converter;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.schonherz.administration.persistence.entities.User;
import hu.schonherz.administration.serviceapi.dto.UserDTO;

public class UserConverter {

	static Mapper mapper = new DozerBeanMapper();

	public static UserDTO toVo(User user) {
		if (user == null) {
			return null;
		}
		return mapper.map(user, UserDTO.class);
	}

	public static User toEntity(UserDTO UserDTO) {
		if (UserDTO == null) {
			return null;
		}
		return mapper.map(UserDTO, User.class);
	}

	public static List<UserDTO> toVo(List<User> user) {
		List<UserDTO> rv = new ArrayList<>();
		for (User users : user) {
			rv.add(toVo(users));
		}
		return rv;
	}

	public static List<User> toEntity(List<UserDTO> user) {
		List<User> rv = new ArrayList<>();
		for (UserDTO users : user) {
			rv.add(toEntity(users));
		}
		return rv;
	}
	
}
