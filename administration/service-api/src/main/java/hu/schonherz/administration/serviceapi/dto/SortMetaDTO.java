package hu.schonherz.administration.serviceapi.dto;

import java.io.Serializable;

public class SortMetaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7500621639465487865L;

	private String columnName;
	private CustomSortOrder order;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public CustomSortOrder getOrder() {
		return order;
	}

	public void setOrder(CustomSortOrder order) {
		this.order = order;
	}

}
