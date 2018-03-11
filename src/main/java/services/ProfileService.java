package services;

import dto.ProfileDto;
import entities.Profile;
import javax.ejb.Local;
import java.util.List;

@Local
public interface ProfileService {
	String test();
	void updateProfile(ProfileDto profile);
	void createProfile(Profile profile);
	void followProfile(long profileId, long profileToFollowId);
	Profile getProfile(long profileId);
	List<Profile> getProfiles(String name);
	List<ProfileDto> getFollowers(long profileId);
	List<ProfileDto> getFollowing(long profileId);
}
