package dao;

import entities.Role;

import java.util.List;


public interface RoleDao {
	List<Role> getAllRoles();
	List<Role> getProfileRoles(long profileId);
}
