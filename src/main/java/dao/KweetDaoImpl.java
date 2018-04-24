package dao;

import entities.Kweet;
import entities.Profile;

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
				"SELECT * FROM kwetter_db.kweet WHERE owner_id = ?", Kweet.class);
		q.setParameter(1, profileId);
		return (List<Kweet>) q.getResultList();
	}

	@Override
	public List<Kweet> getAllKweets() {
		TypedQuery<Kweet> query =
				entityManager.createNamedQuery("Kweet.getAllKweets", Kweet.class);
		return query.getResultList();
	}

	@Override
	public void likeKweet(Kweet kweet, Profile profile) {
		List<Profile> likedBy = kweet.getLikedBy();
		likedBy.add(profile);
		kweet.setLikedBy(likedBy);
		entityManager.merge(kweet);
	}

	@Override
	public void unlikeKweet(long kweetId, long profileId) {
		Query q = entityManager.createNativeQuery("DELETE FROM kwetter_db.kweet_likes " +
						"WHERE kweet_likes.kweet_id = ? AND kweet_likes.profile_id = ?");
		q.setParameter(1, kweetId);
		q.setParameter(2, profileId);
		q.executeUpdate();
	}

	public List<Kweet> getKweetsByIds(List<Long> kweetIds) {
		return null;
	}

	public void deleteKweet(Kweet kweet) {
		TypedQuery<Kweet> query =
				entityManager.createNamedQuery("Kweet.getKweet", Kweet.class);
		Kweet k = query.setParameter("id", kweet.getId()).getSingleResult();
		entityManager.remove(k);
	}

	public Kweet getKweet(long kweetId){
		TypedQuery<Kweet> query =
				entityManager.createNamedQuery("Kweet.getKweet", Kweet.class);
		return query.setParameter("id", kweetId).getSingleResult();
	}
}
