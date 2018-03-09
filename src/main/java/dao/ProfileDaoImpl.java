package dao;

import entities.Profile;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ProfileDaoImpl implements ProfileDao {

	@PersistenceContext(name = "kwetterPU")
	EntityManager entityManager;

	public void createProfile(Profile profile){
		this.entityManager.persist(profile);
		this.entityManager.flush();
	}

	public void followProfile(Profile myProfile, Profile profileToFollow){
		myProfile.getFollowing().add(profileToFollow);
		entityManager.merge(myProfile);
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

	public List<Profile> getFollowing(long profileId) {
		Query q = entityManager.createNativeQuery("SELECT * FROM kwetter_db.profile p WHERE id IN (SELECT kwetter_db.profile_following.following_id FROM kwetter_db.profile_following WHERE kwetter_db.profile_following.profile_id = ?)", "ProfileMapping");
		q.setParameter(1, profileId);
		return (List<Profile>) q.getResultList();
	}
}
