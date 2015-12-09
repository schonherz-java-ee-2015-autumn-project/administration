package hu.schonherz.administration.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.apache.log4j.Logger;
import org.hibernate.annotations.Where;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.administration.persistence.dao.RoleDao;
import hu.schonherz.administration.persistence.dao.UserDao;
import hu.schonherz.administration.persistence.dao.helper.UserSpecification;
import hu.schonherz.administration.persistence.entities.Role;
import hu.schonherz.administration.persistence.entities.User;
import hu.schonherz.administration.service.converter.UserConverter;
import hu.schonherz.administration.serviceapi.UserService;
import hu.schonherz.administration.serviceapi.dto.UserDTO;



@Stateless(mappedName = "UserService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
@Local(UserService.class)
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Autowired
	RoleDao roleDao;

	@Override
	public UserDTO findUserByName(String name) throws Exception {
		User user = userDao.findByUsername(name);
		return UserConverter.toVo(user);
	}

	@Override
	public UserDTO registrationUser(UserDTO userDTO) throws Exception {

		User user = userDao.save(UserConverter.toEntity(userDTO));
		List<Role> roles = user.getRoles();
		if (roles == null || roles.isEmpty()) {
			roles = new ArrayList<>();
		}

		Role role = getUserRole();

		roles.add(role);

		user.setRoles(roles);

		user = userDao.save(user);

		return UserConverter.toVo(user);

	}

	private Role getUserRole() {
		Role role = roleDao.findByName("ROLE_USER");
		if (role == null) {
			role = new Role();
			role.setName("ROLE_USER");
			role = roleDao.save(role);
		}
		return role;
	}

	

	@Override
	public Integer getUserCount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO saveUser(UserDTO selectedUser) {
		return selectedUser;
		// TODO Auto-generated method stub

	}

	@Override
	public UserDTO findById(Long id) {
		return UserConverter.toVo(userDao.findOne(id));
	}

	@Override
	public List<UserDTO> getUsers() {

		return UserConverter.toVo(userDao.findAll());
	}

	@Override
	public List<UserDTO> getUserList(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		String order;
		String name;
		String username;
		String phoneNumber;
		switch ( sortOrder){
		case ASCENDING: order = "asc"; break;
		case DESCENDING: order = "desc"; break;
		case UNSORTED: order = "asc"; break;
		}
		
		Specification<User> spec = null;
		
		if(filters.containsKey("name")){
			name = (String) filters.get("name");
			spec = Specifications.where(UserSpecification.nameLike(name));
		}

		if(filters.containsKey("phoneNumber")){
			phoneNumber = (String) filters.get("phoneNumber");
			spec =Specifications.where(spec).and(UserSpecification.phoneNumberLike(phoneNumber));
		}

		if(filters.containsKey("username")){
			username = (String) filters.get("username");
			spec =Specifications.where(spec).and(UserSpecification.usernameLike(username));
		}
		
		return UserConverter.toVo(userDao.findAll(spec));
	}

}
