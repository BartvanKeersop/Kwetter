package dao;

import entities.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class RoleDaoImpl implements RoleDao {

	@PersistenceContext(name = "kwetterPU")
	EntityManager entityManager;

	public List<Role> getAllRoles() {
		return null;
	}
	public List<Role> getProfileRoles(long profileId) {return null; }

}
