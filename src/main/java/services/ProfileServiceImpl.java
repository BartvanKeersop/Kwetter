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

	public ProfileDao getProfileDao(){
		return profileDao;
	}

	@Override
	public void updateProfile(long profileId, ProfileDto profileDto) {
		Profile updatedProfile = getProfile(profileId);

		updatedProfile.setUsername(profileDto.getUsername());
		updatedProfile.setBiography(profileDto.getBiography());
		updatedProfile.setWebsite(profileDto.getWebsite());
		updatedProfile.setLocation(profileDto.getLocation());

		profileDao.updateProfile(updatedProfile);
	}

	@Override
	public void createProfile(Profile profile) {
		profileDao.createProfile(profile);
	}

	@Override
	public void followProfile(long myProfileId, long profileToFollowId) {
		profileDao.followProfile(
				profileDao.getProfile(myProfileId),
				profileDao.getProfile(profileToFollowId));
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

	public List<ProfileDto> getAll(){
		List<ProfileDto> profileDtos = new ArrayList<>();

		for(Profile p : profileDao.getAll()){
			profileDtos.add(new ProfileDto(p));
		}
		return profileDtos;
	}

	public void deleteProfile(long profileId){
		profileDao.deleteProfile(profileId);
	}
}
