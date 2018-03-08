package services;

import entities.Kweet;
import security.Permissions;

import java.util.List;

public interface KweetService {
	void createKweet(long profileId, Kweet kweet);
	List<Kweet> getMyFeedKweets(long profileId);
	void deleteKweet(long profileId, Kweet kweet, List<Permissions> permissions);
	List<Kweet> getMyLast10Kweets(long profileId);

}
