package dao;

import entities.Kweet;
import entities.Profile;

import java.util.List;

public interface KweetDao {
	void createKweet(Kweet kweet);
	List<Kweet> getKweetsByIds(List<Long> profileIds);
	void deleteKweet(Kweet kweet);
	List<Kweet> getMyLast10Kweets(long profileId);
}
