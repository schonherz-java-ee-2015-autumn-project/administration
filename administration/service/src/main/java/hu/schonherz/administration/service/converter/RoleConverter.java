package hu.schonherz.administration.service.converter;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.schonherz.administration.persistence.entities.Role;
import hu.schonherz.administration.serviceapi.dto.RoleDTO;


public class RoleConverter {

	static Mapper mapper = new DozerBeanMapper();

	public static RoleDTO toVo(Role Role) {
		if (Role == null) {
			return null;
		}
		return mapper.map(Role, RoleDTO.class);
	}

	public static Role toEntity(RoleDTO RoleDTO) {
		if (RoleDTO == null) {
			return null;
		}
		return mapper.map(RoleDTO, Role.class);
	}

	public static List<RoleDTO> toVo(List<Role> Role) {
		List<RoleDTO> rv = new ArrayList<>();
		for (Role Roles : Role) {
			rv.add(toVo(Roles));
		}
		return rv;
	}

	public static List<Role> toEntity(List<RoleDTO> Role) {
		List<Role> rv = new ArrayList<>();
		for (RoleDTO Roles : Role) {
			rv.add(toEntity(Roles));
		}
		return rv;
	}
}
