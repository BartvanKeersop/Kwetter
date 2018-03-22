package controllers;

import services.ProfileService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@SessionScoped
@ManagedBean(name = "adminController")
public class AdminController implements Serializable {

	@Inject
	ProfileService profileService;

	private String message;

	public String getMessage() {
		return "Hello world message!";
	}

	public String welcome(){
		return "Welcome!";
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
