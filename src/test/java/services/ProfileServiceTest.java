package services;

import dto.ProfileDto;
import entities.Profile;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import javax.inject.Inject;
import javax.persistence.*;
import java.util.List;


@RunWith(Arquillian.class)
public class ProfileServiceTest {
	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class)
					.addPackages(true, "dao", "dto", "entities", "filters", "rest", "security", "services")
					.addAsResource("test-persistence.xml", "META-INF/persistence.xml")
					.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	private static EntityManager entityManager;

	private static Profile p1;
	private static Profile p2;
	private static String name1;
	private static String name2;
	private static String email1;
	private static String email2;
	private static String password;

	@BeforeClass
	public static void setup(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("KwetterTestPu");
		entityManager = factory.createEntityManager();
		//profileService.getProfileDao().setEntityManager(entityManager);

		name1 = "Bart";
		name2 = "Jules";
		email1 = "Bart@email.com";
		email2 = "Jules@email.com";
		password = "password";

		p1 = new Profile(email1,password, name1);
		p2 = new Profile(email2, password, name2);
		p1.getFollowing().add(p2);

		entityManager.persist(p1);
		entityManager.persist(p2);
		entityManager.flush();
	}

	@Inject
	ProfileService profileService;

	@Test
	public void hello_world(){
		String response = profileService.test();
		Assert.assertEquals("Hello world", response );
	}

	@Test
	public void getProfile(){
		Profile profile = profileService.getProfile(p1.getId());
		Assert.assertTrue(profile.getEmail().equals(p1.getEmail()));
	}

	@Test
	public void createProfile(){
		String email = "email@email.com";

		//First test if the profile doesn't already exist
		Query q1 = entityManager.createNativeQuery("SELECT email FROM kwetter_test_db.profile WHERE email = ?", "ProfileMapping");
		q1.setParameter(1, email);
		Assert.assertTrue(q1.getResultList().size() == 0);

		profileService.createProfile(
				new Profile(email, "password", "newUser"));

		//Test if the profile is created
		Query q2 = entityManager.createNativeQuery("SELECT email FROM kwetter_test_db.profile WHERE email = ?", "ProfileMapping");
		q2.setParameter(1, email);
		Assert.assertTrue(q2.getResultList().size() == 1);
	}

	@Test
	//Todo: test on multiple fields
	public void testUpdateProfile(){
		ProfileDto profileDto = new ProfileDto(p1);
		String oldBio = profileDto.getBiography();
		String newBio = "This is a new biography.";
		profileDto.setBiography(newBio);

		profileService.updateProfile(p1.getId(), profileDto);
		Profile p = entityManager.find(Profile.class, p1.getId());
		Assert.assertTrue(p.getBiography().equals(newBio));
	}

	@Test
	public void testGetFollowers(){
		List<Profile> profiles1 = p1.getFollowers();
		List<ProfileDto> profiles2 = profileService.getFollowers(p1.getId());
		Assert.assertTrue(profiles1.size() == profiles2.size());
	}

	@Test
	public void testFollowProfile(){
		Query q1 = entityManager.createNativeQuery("SELECT * FROM kwetter_db.profile " +
						"WHERE id IN " +
						"(SELECT kwetter_db.follow.following_id " +
						"FROM kwetter_db.follow " +
						"WHERE kwetter_db.follow.follower_id = ?)",
				"ProfileMapping");

		q1.setParameter(1, p2.getId());
		Assert.assertTrue(q1.getResultList().size() == 0);

		profileService.followProfile(p2.getId(), p1.getId());

		Query q2 = entityManager.createNativeQuery("SELECT * FROM kwetter_db.profile " +
						"WHERE id IN " +
						"(SELECT kwetter_db.follow.following_id " +
						"FROM kwetter_db.follow " +
						"WHERE kwetter_db.follow.follower_id = ?)",
				"ProfileMapping");

		q1.setParameter(1, p2.getId());
		Assert.assertTrue(q1.getResultList().size() == 1);
	}

	@Test
	public void testGetProfiles(){
		Assert.assertTrue(profileService.getProfiles(p1.getUsername()).size() == 1);
	}
}
