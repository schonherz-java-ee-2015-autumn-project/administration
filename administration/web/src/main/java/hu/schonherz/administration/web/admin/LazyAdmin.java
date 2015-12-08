package hu.schonherz.administration.web.admin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
		this.setRowCount(pageSize);
		
		
		Logger log = Logger.getLogger(this.getClass().getName());
		log.debug("FILTEREK száma:" +  filters.size());
		for(Entry<String, Object> e: filters.entrySet()){
			log.debug("nincs " + e.getKey() + " érték "+ e.getValue());
		}
		
		if (userService != null) {
			List<UserDTO> list = userService.getUserList(first, pageSize, sortField, sortOrder, filters);
			if (list == null || list.isEmpty()) {
				return Collections.emptyList();
			} else {
				return list;
			}
		} else {
			return Collections.emptyList();
		}


	}
}