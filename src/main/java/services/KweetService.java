package services;

import entities.Kweet;
import java.util.List;

public interface KweetService {
	List<Kweet> getAllKweets();
	void postTweet();
	List<Kweet> getMyFeedKweets(long profileId);
	void deleteKweet(long kweetId);
}
