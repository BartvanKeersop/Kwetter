package dao;

import entities.Kweet;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class KweetDaoImpl implements KweetDao {

	@PersistenceContext(name = "kwetterPU")
	EntityManager entityManager;

	public void createKweet(Kweet kweet) {
		entityManager.persist(kweet);
	}

	public List<Kweet>  getKweetsByProfileId(long profileId){
		Query q = entityManager.createNativeQuery(
				"SELECT * FROM kwetter_db.kweet WHERE owner_profile_id = ?", Kweet.class);
		q.setParameter(1, profileId);
		return (List<Kweet>) q.getResultList();
	}

	@Override
	public List<Kweet> getAllKweets() {
		TypedQuery<Kweet> query =
				entityManager.createNamedQuery("Kweet.getAllKweets", Kweet.class);
		return query.getResultList();
	}

	public List<Kweet> getKweetsByIds(List<Long> kweetIds) {
		TypedQuery<Kweet> query =
				entityManager.createNamedQuery("Kweet.getFeedKweets", Kweet.class);
		return query.setParameter("ids", kweetIds).getResultList();
	}

	public void deleteKweet(Kweet kweet) {
		entityManager.remove(kweet);
	}
}
