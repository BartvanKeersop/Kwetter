package controllers;

import entities.Kweet;
import entities.Profile;
import services.KweetService;
import services.ProfileService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@SessionScoped
@ManagedBean(name = "profileController")
public class ProfileController {

	public long getProfileId() {
		return profileId;
	}

	public void setProfileId(long profileId) {
		this.profileId = profileId;
	}

	private long profileId;

	@EJB
	ProfileService profileService;

	@EJB
	KweetService kweetService;

	public Profile getProfile(){
		return profileService.getProfile(profileId);
	}

	public List<Kweet> getKweetsByProfileId(){
		return kweetService.getMyKweets(profileId);
	}

	public void onload(){
		System.out.println("LOADED");
	}

	public void deleteKweet(long kweetId){
		Kweet kweet = new Kweet();
		kweet.setId(kweetId);
		kweetService.deleteKweetAsAdmin(kweet);
	}
}
