package dao;

import entities.Kweet;
import entities.Profile;

import java.util.List;

public interface KweetDao {
	Kweet getKweet(long kweetId);
	void createKweet(Kweet kweet);
	List<Kweet> getKweetsByIds(List<Long> profileIds);
	void deleteKweet(Kweet kweet);
	List<Kweet> getKweetsByProfileId(long profileId);
	List<Kweet> getAllKweets();
	void likeKweet(Kweet kweet, Profile profile);
	void unlikeKweet(long kweetId, long profileId);
}
