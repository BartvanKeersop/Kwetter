package datagenerator;

import dao.KweetDao;
import dao.ProfileDao;
import entities.Kweet;
import entities.Profile;
import services.ProfileService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.*;

@Stateless
public class DataGenerator implements IDataGenerator {

	@Inject
	ProfileDao profileDao;

	@Inject
	KweetDao kweetDao;

	public DataGenerator(){}

	public void doNothing(){

	}

	public void generateData(){
		System.out.println("---GENERATING PROFILES---");
		List<Profile> profiles = generateProfiles();
		System.out.println("---GENERATING FOLLOWERS---");
		generateFollowers(profiles);
		System.out.println("---GENERATING KWEETS---");
		generateKweets(profiles);
	}

	public void generateFollowers(List<Profile> profiles){
		/*
		int size = (profiles.size() - 10);
		Random random = new Random();

		for(int i = 0; i < size ; i ++){
			Set<Integer> randoms = generateRandomSet(size, 10);
			for (int j : randoms) {
				profileDao.followProfile(profiles.get(i), profiles.get(j));
			}
		}
		*/
	}

	public Set<Integer> generateRandomSet(int rngMax, int ammount){
		Random r = new Random();
		Set<Integer> list2 = new LinkedHashSet<Integer>();
		while( list2.size() < ammount ) {
			list2.add(r.nextInt(rngMax));
		}
		return list2;
	}


	public void generateFollowing(){

	}

	public List<Profile> generateProfiles(){
		List<Profile> generatedProfiles = new ArrayList<>();
		int size = 30;

		Profile myProfile = new Profile();
		myProfile.setEmail("email");
		myProfile.setUsername("Bart");
		myProfile.setPassword("password");
		myProfile.setBiography("Hi, I'm Bart!");
		myProfile.setLocation("Eindhoven");
		generatedProfiles.add(myProfile);

		profileDao.createProfile(myProfile);

			for (int i = 0; i < size; i++) {
				String username = generateUsername();
				System.out.println(username);

				Profile p = new Profile();
				p.setUsername(username);
				p.setBiography(generateText());
				p.setEmail(username + "@email.com");
				p.setPassword(username);
				p.setLocation(generateLocation());
				profileDao.createProfile(p);
				generatedProfiles.add(p);
			}
			return generatedProfiles;
	}

	private void generateKweets(List<Profile> profiles){
		int size = 100;
		Random r = new Random();

		for (int i = 0; i < size; i++) {
			System.out.println(i);
			Kweet k = new Kweet();
			k.setText(generateText());
			k.setCreationDate(getRandomDate());
			k.setOwner(profiles.get(r.nextInt(profiles.size() - 1)));
			kweetDao.createKweet(k);
		}
	}

	private LocalDateTime getRandomDate(){
		LocalDateTime date = LocalDateTime.now();
		return date.minusDays(getRandom());
	}

	private int getRandom(){
		Random random = new Random();
		return random.nextInt(7) + 1;
	}

	private int getBigRandom(){
		Random random = new Random();
		return random.nextInt(999) + 1;
	}

	private String generateText(){
		String[] randomText = new String[]{"Hi ", "I'm ", "Happy ", "Sad ", "Want ", "To ", "Know ", "Bier "};
		Random random = new Random();
		String text = "";
		int textlength = 50;
		for(int i = 0; i < textlength; i++){
			text += randomText[getRandom()];
		}
		return text;
	}

	private String generateUsername(){
		String[] username_1 = new String[]{"Flying", "Sitting", "Sad", "Happy", "Fat", "Skinny", "Cute", "Cool"};
		String[] username_2 = new String[] {"Fox", "Zebra", "Elephant", "Monkey", "Lion", "Potato", "Banana", "Beerlover"};
		String generated = "";
		generated += username_1[getRandom()];
		generated += username_2[getRandom()];
		generated += Integer.toString(getBigRandom());
		return generated;
	}

	private String generateLocation(){
		String[] location = new String[]{"Eindhoven", "Amsterdam", "Paris", "Berlin", "Venice", "New York", "Brussels", "Moscow"};
		return location[getRandom()];
	}
}
