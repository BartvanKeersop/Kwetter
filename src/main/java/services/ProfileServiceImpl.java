package services;

import entities.Profile;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

@Stateless
//TODO: Store own profileId in session
public class ProfileServiceImpl implements ProfileService {

	@PersistenceContext(name = "kwetterPU")
	EntityManager entityManager;

	public void createProfile(Profile profile){
		this.entityManager.persist(profile);
		this.entityManager.flush();
	}

	public void followProfile(Profile profileToFollow){
		/*
		Profile myProfile = entityManager.find(Profile.class, mp.getId());
		Profile profileToFollow = entityManager.find(Profile.class, ptf.getId());
		myProfile.getFollowing().add(profileToFollow);
		entityManager.merge(myProfile);
		*/
	}

	public void updateUsername(long profileId, String newName) {
		Profile profile = entityManager.find(Profile.class, profileId);
		profile.setUsername(newName);
		entityManager.merge(profile);
	}

	public Profile getProfile(long profileId){
		TypedQuery<Profile> query =
				entityManager.createNamedQuery("Profile.getProfileById", Profile.class);
		return query.setParameter("profileId", profileId).getSingleResult();
	}

	public List<Profile> getProfiles(String username) {
		TypedQuery<Profile> query =
				entityManager.createNamedQuery("Profile.getProfiles", Profile.class);
		return query.setParameter("username", username).getResultList();
	}

	//TODO: implement named query
	public List<Profile> getFollowers(long profileId) {
		TypedQuery<Profile> query =
				entityManager.createNamedQuery("Profile.getFollowers", Profile.class);
		return query.setParameter("id", profileId).getResultList();
	}

	//TODO: implement named query
	public List<Profile> getFollowing(long profileId) {
		TypedQuery<Profile> query =
				entityManager.createNamedQuery("Profile.getFollowing", Profile.class);
		return query.setParameter("id", profileId).getResultList();
	}

}
