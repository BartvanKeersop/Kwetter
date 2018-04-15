package controllers;

import dto.ProfileDto;
import services.ProfileService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@ManagedBean(name = "adminController")
public class AdminController implements Serializable {
	@Inject
	ProfileService profileService;

	public String welcome(){
		return "Welcome!";
	}


	public List<ProfileDto> getAll(){
		return profileService.getAll();
	}

	public void deleteUser(long profileId){
		profileService.deleteProfile(profileId);
	}
}
