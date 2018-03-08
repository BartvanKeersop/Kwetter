package services;

import dao.KweetDao;
import dao.ProfileDao;
import entities.Kweet;
import entities.Profile;
import security.Permissions;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class KweetServiceImpl implements KweetService {

	@EJB
	KweetDao kweetDao;

	@EJB
	ProfileDao profileDao;

	@Override
	public void createKweet(long profileId, Kweet kweet) {
		kweet.setId(profileId);
		kweetDao.createKweet(kweet);
	}

	@Override
	public List<Kweet> getMyFeedKweets(long profileId) {
		List<Profile> following = profileDao.getFollowing(profileId);

		List<Long> feedIds = new ArrayList<>();
		feedIds.add(profileId);

		for (Profile p : following){
			feedIds.add(p.getId());
		}

		return kweetDao.getKweetsByIds(feedIds);
	}

	@Override
	public void deleteKweet(long profileId, Kweet kweet, List<Permissions> permissions) {
		kweetDao.deleteKweet(kweet);
	}

	@Override
	public List<Kweet> getMyLast10Kweets(long profileId) {
		return kweetDao.getMyLast10Kweets(profileId);
	}
}
