package services;

import entities.Profile;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProfileServiceImpl implements ProfileService {

	@PersistenceContext(name = "kwetterPU")
	EntityManager entityManager;

	public void create(){
		this.entityManager.persist(new Profile());
		this.entityManager.flush();
	}

}
