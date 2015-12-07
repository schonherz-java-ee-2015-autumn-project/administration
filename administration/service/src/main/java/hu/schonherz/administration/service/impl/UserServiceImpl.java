package hu.schonherz.administration.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.administration.persistence.dao.RoleDao;
import hu.schonherz.administration.persistence.dao.UserDao;
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
	public UserDTO registrationUser(UserDTO UserDTO) throws Exception {

		User user = userDao.save(UserConverter.toEntity(UserDTO));
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
	public List<UserDTO> getUserList(int i, int pageSize, String sortField, int dir, String filter,
			String filterColumnName) {
		// TODO Auto-generated method stub
		return null;
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

}
