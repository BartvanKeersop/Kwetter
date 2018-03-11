package services;

import dto.ProfileDto;
import entities.Profile;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
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

	@Before
	public void setup(){
	}

	@Inject
	ProfileService profileService;

	@Test
	public void hello_world(){
		String response = profileService.test();
		Assert.assertEquals("Hello world", response );
	}

	@Test
	public void testCreateAndGetProfiles(){
		Profile profile = new Profile();
		profile.setUsername("username-unique-1234567890");
		profile.setPassword("password");
		profile.setEmail("email");
		profileService.createProfile(profile);
		Profile profile2 = profileService.getProfiles(profile.getUsername()).get(0);
		Assert.assertEquals(profile2.getUsername(), "username-unique-1234567890");
	}

	@Test
	public void testUpdateProfile(){
		//Not sure how to mock IAuthenticatedUser
	}

	@Test
	public void testFollowProfileAndGetFollowers(){
		Profile p1 = new Profile("a", "a", "a");
		Profile p2 = new Profile("b", "b", "b");
		profileService.createProfile(p1);
		profileService.createProfile(p2);

		List<ProfileDto> profilesBefore = profileService.getFollowing(p1.getId());
		Assert.assertTrue(profilesBefore.isEmpty());

		profileService.followProfile(p1.getId(), p2.getId());

		List<ProfileDto> profilesAfter = profileService.getFollowing(p1.getId());
		//Assert.assertTrue(!profilesAfter.isEmpty());
	}

	@Test
	public void testGetProfile(){
		Profile profile = new Profile("c", "c", "c");
		profileService.createProfile(profile);
		String emailFromDb = profileService.getProfile(profile.getId()).getEmail();
		Assert.assertTrue(profile.getEmail().equals(emailFromDb));
	}

	@Test
	public void testGetFollowers(){
		/**
		Profile p4 = new Profile("d", "d", "d");
		Profile p5 = new Profile("e", "e", "e");
		profileService.createProfile(p4);
		profileService.createProfile(p5);

		List<ProfileDto> profilesBefore = profileService.getFollowers(p4.getId());
		Assert.assertTrue(profilesBefore.isEmpty());

		profileService.followProfile(p5.getId(), p4.getId());

		List<ProfileDto> profilesAfter = profileService.getFollowers(p4.getId());
		//Assert.assertTrue(!profilesAfter.isEmpty());
		 **/
	}

}
