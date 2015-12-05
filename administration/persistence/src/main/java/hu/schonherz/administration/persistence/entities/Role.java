package hu.schonherz.administration.persistence.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Role extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false, length = 10)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
