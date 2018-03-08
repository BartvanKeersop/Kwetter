package services;

import dao.ProfileDao;
import entities.Profile;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

@Stateless
public class ProfileServiceImpl implements ProfileService {

	@EJB
	ProfileDao profileDao;

	@Override
	public void createProfile(Profile profile) {
		profileDao.createProfile(profile);
	}

	@Override
	public void updateUsername(Profile profile) {
		profileDao.updateUsername(profile.getId(), profile.getUsername());
	}

	@Override
	public void followProfile(long profileId, Profile profileToFollow) {
		profileDao.followProfile(
				profileDao.getProfile(profileId),
				profileToFollow);
	}

	@Override
	public Profile getProfile(long profileId) {
		return profileDao.getProfile(profileId);
	}

	@Override
	public List<Profile> getProfiles(String name) {
		return profileDao.getProfiles(name);
	}

	@Override
	public List<Profile> getFollowers(long profileId) {
		return profileDao.getFollowers(profileId);
	}

	@Override
	public List<Profile> getFollowing(long profileId) {
		return profileDao.getFollowing(profileId);
	}
}
