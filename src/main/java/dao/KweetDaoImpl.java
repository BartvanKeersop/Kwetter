package dao;

import entities.Kweet;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class KweetDaoImpl implements KweetDao {

	@PersistenceContext(name = "kwetterPU")
	EntityManager entityManager;

	public void createKweet(Kweet kweet) {
		entityManager.persist(kweet);
	}

	public List<Kweet> getKweetsByIds(List<Long> kweetIds) {
		TypedQuery<Kweet> query =
				entityManager.createNamedQuery("Kweet.getFeedKweets", Kweet.class);
		return query.setParameter("owner", kweetIds).getResultList();
	}

	public void deleteKweet(Kweet kweet) {
		entityManager.remove(kweet);
	}

	public List<Kweet> getMyLast10Kweets(long profileId) {
		TypedQuery<Kweet> query =
				entityManager.createNamedQuery("Kweet.getMyKweetsByDateDesc", Kweet.class)
						.setMaxResults(10);
		return query.setParameter("id", profileId).getResultList();
	}
}
