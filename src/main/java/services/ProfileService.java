package services;

import entities.Profile;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ProfileService {
	void createProfile();
	void changeUsername(String newName);
	Profile getProfile(long profileId);
	List<Profile> getProfiles(String name);
	List<Profile> getFollowers(Profile profile);
	List<Profile> getFollowing(Profile profile);
}
