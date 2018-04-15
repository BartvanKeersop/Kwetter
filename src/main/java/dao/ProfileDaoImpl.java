package dao;

import entities.Profile;
import entities.Role;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class ProfileDaoImpl implements ProfileDao {

	@PersistenceContext(name = "kwetterPU")
	EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void createProfile(Profile profile){
		this.entityManager.persist(profile);
		this.entityManager.flush();
	}

	public void updateProfile(Profile profile) {
		entityManager.merge(profile);
	}

	public void followProfile(Profile myProfile, Profile profileToFollow){
		myProfile.getFollowing().add(profileToFollow);
		entityManager.merge(myProfile);
		entityManager.flush();
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

	public List<Profile> getFollowers(long profileId) {
		Query q = entityManager.createNativeQuery("SELECT * FROM kwetter_db.profile " +
						"WHERE profile_id IN " +
						"(SELECT kwetter_db.profile_profile.followers_profile_id " +
						"FROM kwetter_db.profile_profile " +
						"WHERE kwetter_db.profile_profile.following_profile_id = ?)",
				Profile.class);
		q.setParameter(1, profileId);
		return (List<Profile>) q.getResultList();
	}

	public List<Profile> getFollowing(long profileId) {
		Query q = entityManager.createNativeQuery("SELECT * FROM kwetter_db.profile " +
				"WHERE profile_id IN " +
				"(SELECT kwetter_db.profile_profile.following_profile_id " +
				"FROM kwetter_db.profile_profile " +
				"WHERE kwetter_db.profile_profile.followers_profile_id = ?)",
				Profile.class);
		q.setParameter(1, profileId);
		return (List<Profile>) q.getResultList();
	}

	public List<Profile> getAll(){
		TypedQuery<Profile> query =
				entityManager.createNamedQuery("Profile.getAll", Profile.class);
		return query.getResultList();
	}

	public void deleteProfile(long profileId){
		Profile profile = getEntityManager().find(Profile.class, profileId);
		getEntityManager().remove(profile);
	}
}
