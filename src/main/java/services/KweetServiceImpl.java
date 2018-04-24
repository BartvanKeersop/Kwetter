package services;

import dao.KweetDao;
import dao.ProfileDao;
import dto.KweetDto;
import dto.ProfileDto;
import entities.Role;
import entities.Kweet;
import entities.Profile;

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
		Profile owner = profileDao.getProfile(profileId);
		kweet.setOwner(owner);
		kweetDao.createKweet(kweet);
	}

	@Override
	public List<KweetDto> getMyFeedKweets(long profileId) {
		//TODO: add likes
		List<KweetDto> kweetDtos = new ArrayList<>();

		//Get following by profile Id
		List<Profile> following = profileDao.getFollowing(profileId);
		System.out.println(following.size());

		//Get kweets by following
		for (Profile p : following){

			ProfileDto profileDto = new ProfileDto(p);

			List<Kweet> kweets = kweetDao.getKweetsByProfileId(p.getId());
			System.out.println(kweets.size());

			//Convert kweets to dtos
			kweetDtos = mapKweetsToKweetDtos(kweets);

			//Add profileDto to kweet
			kweetDtos = addProfileDtoToKweetDto(kweetDtos, new ProfileDto(p));
		}
		return kweetDtos;
	}

	@Override
	public List<Kweet> getMyKweets(long profileId) {
		return kweetDao.getKweetsByProfileId(profileId);
	}

	public List<Kweet> getAllKweets() {
		return kweetDao.getAllKweets();
	}

	private List<KweetDto> mapKweetsToKweetDtos(List<Kweet> kweets){
		List<KweetDto> kweetDtos = new ArrayList<>();
		for (Kweet k : kweets){
			kweetDtos.add(new KweetDto(k));
		}
		return kweetDtos;
	}

	private List<KweetDto> addProfileDtoToKweetDto(List<KweetDto> kweetDtos, ProfileDto profileDto){
		for (KweetDto k : kweetDtos){
			k.setOwner(profileDto);
		}
		return kweetDtos;
	}

	@Override
	public void deleteKweet(long profileId, Kweet kweet, List<Role> permissions) {
		kweetDao.deleteKweet(kweet);
	}

	@Override
	public void deleteKweetAsAdmin(Kweet kweet) {
		kweetDao.deleteKweet(kweet);
	}

	@Override
	public void likeKweet(long profileId, long kweetId) {
		if(!checkIfAlreadyLiked(profileId, kweetId)) {
			Profile profile = profileDao.getProfile(profileId);
			Kweet kweet = kweetDao.getKweet(kweetId);
			kweetDao.likeKweet(kweet, profile);
		}
	}

	@Override
	public void unlikeKweet(long profileId, long kweetId) {
		if(checkIfAlreadyLiked(profileId, kweetId)) {
			System.out.println("unliking kweet");
			kweetDao.unlikeKweet(kweetId, profileId);
		}
	}

	private boolean checkIfAlreadyLiked(long profileId, long kweetId){
		Kweet kweet = kweetDao.getKweet(kweetId);

		for (Profile profile : kweet.getLikedBy()){
			if(profile.getId() == profileId){
				return true;
			}
		}
		return false;
	}
}
