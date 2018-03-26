package dto;

import entities.Profile;
import entities.Role;

import java.util.List;

public class ProfileDto {
	private Long id;
	private String email;
	private String username;
	private String biography;
	private String website;
	private String location;
	private List<Role> roles;

	public ProfileDto(){

	}

	public ProfileDto(Profile profile){
		this.id = profile.getId();
		this.email = profile.getEmail();
		this.username = profile.getUsername();
		this.biography = profile.getBiography();
		this.website = profile.getWebsite();
		this.location = profile.getLocation();
		this.roles = profile.getRoles();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
