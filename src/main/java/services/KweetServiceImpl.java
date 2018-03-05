package services;

import entities.Kweet;
import entities.Profile;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class KweetServiceImpl implements KweetService {

	@PersistenceContext(name = "kwetterPU")
	EntityManager entityManager;

	public void createKweet(Kweet kweet) {
		entityManager.persist(kweet);
	}

	//TODO: Add own kweets
	public List<Kweet> getMyFeedKweets(Profile profile) {
		TypedQuery<Kweet> query =
				entityManager.createNamedQuery("Kweet.getFeedKweets", Kweet.class);
		return query.setParameter("owner", profile.getFollowing()).getResultList();
	}

	public void deleteKweet(Kweet kweet) {
		entityManager.remove(kweet);
	}

	public List<Kweet> getMyLast10Kweets() {
		long myId= 0; //TODO: get Id from session
		TypedQuery<Kweet> query =
				entityManager.createNamedQuery("Kweet.getMyKweetsByDateDesc", Kweet.class)
						.setMaxResults(10);
		return query.setParameter("id", myId).getResultList();
	}
}
