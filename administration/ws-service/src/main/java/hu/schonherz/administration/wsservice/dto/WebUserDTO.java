package hu.schonherz.administration.wsservice.dto;

import java.io.Serializable;
import java.util.Date;

public class WebUserDTO implements Serializable {

	private static final long serialVersionUID = 5932000328505763772L;

	private Long id;
	private String name;
	private String username;
	private String password;
	private boolean remove;
	private Date moddate;

	public boolean isRemove() {
		return remove;
	}

	public void setRemove(boolean remove) {
		this.remove = remove;
	}

	public Date getModdate() {
		return moddate;
	}

	public void setModdate(Date moddate) {
		this.moddate = moddate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
