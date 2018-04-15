package services;

import dao.ProfileDao;
import dto.ProfileDto;
import entities.Profile;
import javax.ejb.Local;
import java.util.List;

public interface ProfileService {
	void updateProfile(long profileId, ProfileDto profile);
	void createProfile(Profile profile);
	void followProfile(long profileId, long profileToFollowId);
	void deleteProfile(long profileId);
	Profile getProfile(long profileId);
	List<Profile> getProfiles(String name);
	List<ProfileDto> getFollowers(long profileId);
	List<ProfileDto> getFollowing(long profileId);
	public ProfileDao getProfileDao();
	List<ProfileDto> getAll();
}
