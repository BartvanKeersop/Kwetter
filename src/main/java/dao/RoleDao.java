package dao;

import entities.Role;

import java.util.List;


public interface RoleDao {
	void setAdminRole(long profileId);
	void removeAdminRole(long profileId);
}
