package services;

import entities.Kweet;
import entities.Profile;

import java.util.List;

public interface KweetService {
	void createKweet(Kweet kweet);
	List<Kweet> getMyFeedKweets(Profile profile);
	void deleteKweet(Kweet kweet);
	List<Kweet> getMyLast10Kweets();

}
