package dao;

import entities.Profile;
import entities.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class RoleDaoImpl implements RoleDao {

	@PersistenceContext(name = "kwetterPU")
	EntityManager entityManager;

	@Override
	public void setAdminRole(long profileId) {

	}

	@Override
	public void removeAdminRole(long profileId) {

	}
}
