package hu.schonherz.administration.web.admin;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import hu.schonherz.administration.serviceapi.UserService;
import hu.schonherz.administration.serviceapi.dto.UserDTO;

@Named
@EJB(name = "ejb.UserService", beanInterface = UserService.class)
public class LazyAdmin extends LazyDataModel<UserDTO> {

	@EJB
	UserService userService;

	@Override
	public UserDTO getRowData(String rowKey) {
		return userService.findById(Long.getLong(rowKey));

	}

	@Override
	public Object getRowKey(UserDTO userDTO) {
		return userDTO.getId();
	}

	@Override
	public List<UserDTO> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		
		int userCount = userService.getUserCount();
		int page = first / pageSize;
		
		if (userService != null) {
			List<UserDTO> list = userService.getUserList(page, pageSize, sortField, sortOrder, filters);
			
			if (list == null || list.isEmpty()) {
				return Collections.emptyList();
			} else {
				this.setRowCount(userCount);
				return list;
			}
		} else {
			return Collections.emptyList();
		}


	}
}