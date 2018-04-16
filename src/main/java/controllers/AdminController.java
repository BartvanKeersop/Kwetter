package controllers;

import dto.ProfileDto;
import entities.Kweet;
import entities.Profile;
import services.KweetService;
import services.ProfileService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
@ManagedBean(name = "adminController")
public class AdminController implements Serializable {

	@EJB
	ProfileService profileService;

	public String welcome(){
		return "Welcome!";
	}

	public List<ProfileDto> getAll(){
		List<ProfileDto> allProfiles= profileService.getAll();
		if (allProfiles != null){
			return allProfiles;
		}
		else return new ArrayList<>();
	}

	public void deleteUser(long profileId){
		profileService.deleteProfile(profileId);
	}
}
