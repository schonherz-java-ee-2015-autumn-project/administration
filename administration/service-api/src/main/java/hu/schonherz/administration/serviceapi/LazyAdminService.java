package hu.schonherz.administration.serviceapi;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import hu.schonherz.administration.serviceapi.dto.UserDTO;

/**
 * Dummy implementation of LazyDataModel that uses a list to mimic a real
 * datasource like a database.
 */
public interface LazyAdminService{

	public UserDTO getRowData(String rowKey);
	public Object getRowKey(UserDTO userDTO);
	public List<UserDTO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters);
}