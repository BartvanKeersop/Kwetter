package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
		@NamedQuery(name="Profile.getProfileById",
				query="SELECT p FROM Profile p WHERE p.id = :profileId"),
		@NamedQuery(name="Profile.getProfiles",
				query="SELECT p FROM Profile p WHERE p.username LIKE :username"),
		@NamedQuery(name="Profile.authenticate",
				query="SELECT p FROM Profile p WHERE p.email = :email AND p.password = :password"),
		@NamedQuery(name="Profile.getProfileByToken",
				query="SELECT p FROM Profile p WHERE p.token = :token"),
		@NamedQuery(name="Profile.getAll",
				query="SELECT p FROM Profile p"),
})
public class Profile implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "profile_id")
	private Long id;
	@Column(unique = true, nullable = false)
	private String email;
	@Column(unique = true, nullable = false)
	private String username;
	@Column(nullable = false)
	private String password;

	@ManyToMany(mappedBy = "profiles", cascade = CascadeType.ALL)
	private List<Role> roles;

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Kweet> getMentionedIn() {
		return mentionedIn;
	}

	public void setMentionedIn(List<Kweet> mentionedIn) {
		this.mentionedIn = mentionedIn;
	}

	public List<Kweet> getLikedKweets() {
		return likedKweets;
	}

	public void setLikedKweets(List<Kweet> likedKweets) {
		this.likedKweets = likedKweets;
	}

	@ManyToMany(mappedBy = "mentions")
	private List<Kweet> mentionedIn;

	private String biography;
	private String website;
	private String location;
	private String token;

	@OneToMany
	@JoinTable
	private List<Kweet> kweets;

	@ManyToMany
	private List<Profile> following;

	@ManyToMany(mappedBy = "following")
	private List<Profile> followers;

	@ManyToMany(mappedBy = "likedBy")
	@JsonIgnore
	private List<Kweet> likedKweets;

	public Profile(){
	}

	public Profile(String email, String password, String username){
		this.username = username;
		this.password = password;
		this.email = email;
		this.followers = new ArrayList<>();
		this.following = new ArrayList<>();
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public List<Kweet> getKweets() {
		return kweets;
	}

	public void setKweets(List<Kweet> kweets) {
		this.kweets = kweets;
	}

	@JsonIgnore
	public List<Profile> getFollowing() {
		return following;
	}

	public void setFollowing(List<Profile> following) {
		this.following = following;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<Role> getPermission() {
		return roles;
	}

	public void setPermission(List<Role> permission) {
		this.roles = permission;
	}

	@JsonIgnore
	public List<Profile> getFollowers() { return followers; }

	public void setFollowers(List<Profile> followers) { this.followers = followers; }
}
