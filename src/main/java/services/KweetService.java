package services;

import dto.KweetDto;
import entities.Role;
import entities.Kweet;

import java.util.List;

public interface KweetService {
	void createKweet(long profileId, Kweet kweet);
	List<KweetDto> getMyFeedKweets(long profileId);
	void deleteKweet(long profileId, Kweet kweet, List<Role> permissions);
	List<Kweet> getMyKweets(long profileId);
	List<Kweet> getAllKweets();
	void deleteKweetAsAdmin(Kweet kweet);
}
