package security;

import entities.Role;

import java.util.List;

public class AuthenticatedUser {

	private long id;
	private List<Role> roles;

	public List<Role> getRoles() {
		return roles;
	}

	public void setPermissions(List<Role> role) {
		this.roles = role;
	}

	public AuthenticatedUser() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
