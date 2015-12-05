package hu.schonherz.administration.service.dto;

import java.io.Serializable;

public class RoleDTO implements Serializable {

	private static final long serialVersionUID = -8403753397412273249L;

	private Long id;

	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "RoleVO [id=" + id + ", name=" +name + "]";
	}

}
