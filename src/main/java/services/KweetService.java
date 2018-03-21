package services;

import entities.Role;
import entities.Kweet;

import java.util.List;

public interface KweetService {
	void createKweet(long profileId, Kweet kweet);
	List<Kweet> getMyFeedKweets(long profileId);
	void deleteKweet(long profileId, Kweet kweet, List<Role> permissions);
	List<Kweet> getMyLast10Kweets(long profileId);

}
