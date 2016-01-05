package hu.schonherz.administration.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import hu.schonherz.administration.serviceapi.dto.CustomSortOrder;
import hu.schonherz.administration.serviceapi.dto.UserDTO;
import hu.schonherz.administration.serviceapi.dto.UserRole;

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
		return UserConverter.toVo(userDao.findByUsername(name));
	}
	
	@Override
	public void removeUser(long id) throws Exception {
		User user = userDao.findById(id);
		user.setRemove(true);
		userDao.save(user);
	}


	@Override
	public UserDTO registrationAdmin(UserDTO userDTO) throws Exception {

		User user = userDao.save(UserConverter.toEntity(userDTO));

		List<Role> roles = user.getRoles();
		if (roles == null || roles.isEmpty()) {
			roles = new ArrayList<>();
		}

		Role role = getAdminRole();

		roles.add(role);

		user.setRoles(roles);

		user = userDao.save(user);

		return UserConverter.toVo(user);

	}
	
	@Override
	public UserDTO registrationCourier(UserDTO userDTO) throws Exception {
		
		User user = userDao.save(UserConverter.toEntity(userDTO));
		
		List<Role> roles = user.getRoles();
		if (roles == null || roles.isEmpty()) {
			roles = new ArrayList<>();
		}
		
		Role role = getCourierRole();
		
		roles.add(role);
		
		user.setRoles(roles);
		
		user = userDao.save(user);
		
		return UserConverter.toVo(user);
		
	}
	

	private Role getRestaurantRole() {
		Role role = roleDao.findByName("ROLE_RESTAURANT");
		if (role == null) {
			role = new Role();
			role.setName("ROLE_RESTAURANT");
			role = roleDao.save(role);
		}
		return role;
	}
	
	private Role getAdminRole() {
		Role role = roleDao.findByName("ROLE_ADMIN");
		if (role == null) {
			role = new Role();
			role.setName("ROLE_ADMIN");
			role = roleDao.save(role);
		}
		return role;
	}
	
	private Role getCourierRole() {
		Role role = roleDao.findByName("ROLE_COURIER");
		if (role == null) {
			role = new Role();
			role.setName("ROLE_COURIER");
			role = roleDao.save(role);
		}
		return role;
	}

	@Override
	public int getUserCount() {
		return  (int)userDao.count();	
	}


	@Override
	public UserDTO findById(long id) {
		return UserConverter.toVo(userDao.findOne(id));
	}

	@Override
	public List<UserDTO> getUsers() {

		return UserConverter.toVo(userDao.findAll());
	}

	@Override
	public List<UserDTO> getUserList(int first, int pageSize, String sortField, CustomSortOrder sortOrder,
			Map<String, Object> filters, UserRole role) {
		Pageable pagable = createPageRequest(first, pageSize, sortField, sortOrder);

		Specification<User> spec = buildSpecification(filters);
		Specification<User> roleSpec = buildRoleSpecification(role);
		Specification<User> isDelete = buildRemoveSpecification();
		spec = Specifications.where(spec).and(roleSpec).and(isDelete);
		return UserConverter.toVo(userDao.findAll(spec, pagable).getContent());
	}

	private Specification<User> buildRemoveSpecification() {
		Specification<User> spec = Specifications.where(UserSpecification.isDelete());
		return spec;
	}

	private Pageable createPageRequest(int first, int pageSize, String sortField, CustomSortOrder order) {
		if(order!=null && sortField !=null){
			Sort sort = null;
			if (order.equals(CustomSortOrder.DESC)) {
				sort = new Sort(Sort.Direction.DESC, sortField);
			} else {
				sort = new Sort(Sort.Direction.ASC, sortField);
			}
			return new PageRequest(first, pageSize, sort);
		}else{
			return new PageRequest(first, pageSize);

		}

	}
	
	private Specification<User> buildRoleSpecification(UserRole role){
		Specification<User> spec = null;
		Role roleEntity;
		switch(role){
		case ADMIN:
			roleEntity = roleDao.findByName("ROLE_ADMIN");
			spec = Specifications.where(UserSpecification.hasRole(roleEntity )); break;
		case COURIER:
			roleEntity = roleDao.findByName("ROLE_COURIER");
			spec = Specifications.where(UserSpecification.hasRole(roleEntity )); break;
		case RESTAURANT: 
			roleEntity = roleDao.findByName("ROLE_RESTAURANT");
			spec = Specifications.where(UserSpecification.hasRole(roleEntity )); break;
		}
		return spec;
	}

	@Override
	public int getUserCount(Map<String, Object> filters, UserRole role) {
		return (int)userDao.count(Specifications.where(buildSpecification(filters)).and(buildRoleSpecification(role)).and(buildRemoveSpecification()));
		
	}
	
	private Specification<User> buildSpecification(Map<String, Object> filters){
		Specification<User> spec = null;
		String name;
		String username;
		String phoneNumber;
	
		if (filters.containsKey("name")) {
			name = (String) filters.get("name");
			spec = Specifications.where(UserSpecification.nameLike(name));
		}

		if (filters.containsKey("phoneNumber")) {
			phoneNumber = (String) filters.get("phoneNumber");
			spec = Specifications.where(spec).and(UserSpecification.phoneNumberLike(phoneNumber));
		}

		if (filters.containsKey("username")) {
			username = (String) filters.get("username");
			spec = Specifications.where(spec).and(UserSpecification.usernameLike(username));
		}
		return spec;
	}
	
	@Override
	public void saveUser(UserDTO userDTO) {
		userDao.save(UserConverter.toEntity(userDTO));
	}

	@Override
	public UserDTO saveRestaurantUser(UserDTO userDTO) throws Exception {
		User user = userDao.save(UserConverter.toEntity(userDTO));

		List<Role> roles = user.getRoles();
		if (roles == null || roles.isEmpty()) {
			roles = new ArrayList<>();
		}

		Role role = getRestaurantRole();

		roles.add(role);

		user.setRoles(roles);

		user = userDao.save(user);

		return UserConverter.toVo(user);
	}
	
}
