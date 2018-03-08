package dao;

import entities.Profile;

import java.util.List;

public interface ProfileDao {
	void createProfile(Profile profile);
	void updateUsername(long profileId, String newName);
	void followProfile(Profile myProfile , Profile profileToFollow);
	Profile getProfile(long profileId);
	List<Profile> getProfiles(String name);
	List<Profile> getFollowers(long profileId);
	List<Profile> getFollowing(long profileId);
}
