package services;

import dao.ProfileDao;
import entities.Profile;
import dto.ProfileDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
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
	public List<ProfileDto> getFollowers(long profileId) {
		List<ProfileDto> profileDtos = new ArrayList<>();

		for(Profile p : profileDao.getFollowers(profileId)){
			profileDtos.add(new ProfileDto(p));
		}
		return profileDtos;
	}

	@Override
	public List<ProfileDto> getFollowing(long profileId) {
		List<ProfileDto> profileDtos = new ArrayList<>();

		for(Profile p : profileDao.getFollowing(profileId)){
			profileDtos.add(new ProfileDto(p));
		}
		return profileDtos;
	}
}
