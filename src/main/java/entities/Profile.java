package entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import security.Permissions;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NamedQueries({
		@NamedQuery(name="Profile.getProfileById",
				query="SELECT p FROM Profile p WHERE p.id = :profileId"),
		@NamedQuery(name="Profile.getProfiles",
				query="SELECT p FROM Profile p WHERE p.username LIKE :username"),
		@NamedQuery(name="Profile.getFollowers",
				query="SELECT p.followers FROM Profile p"),
		@NamedQuery(name="Profile.getFollowing",
				query="SELECT p.following FROM Profile p"),
		@NamedQuery(name="Profile.authenticate",
				query="SELECT p FROM Profile p WHERE p.email = :email AND p.password = :password"),
		@NamedQuery(name="Profile.getProfileByToken",
				query="SELECT p FROM Profile p WHERE p.token = :token"),
})
public class Profile implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique = true, nullable = false)
	private String email;
	@Column(unique = true, nullable = false)
	private String username;
	@Column(nullable = false)
	private String password;
	@ElementCollection
	@Enumerated(EnumType.STRING)
	private List<Permissions> permission;
	private String biography;
	private String website;
	private String location;
	private String token;
	@OneToMany
	@JoinTable
	private List<Kweet> kweets;
	@OneToMany
	@JoinTable(name = "profile_followers"
			, joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id", nullable = false)
			, inverseJoinColumns = @JoinColumn(name = "follower_id", referencedColumnName = "id", nullable = false))
	private List<Profile> followers;
	@OneToMany
	@JoinTable(name = "profile_following"
			, joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id", nullable = false)
			, inverseJoinColumns = @JoinColumn(name = "following_id", referencedColumnName = "id", nullable = false))
	private List<Profile> following;

	public Profile(){
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

	public List<Profile> getFollowers() {
		return followers;
	}

	public void setFollowers(List<Profile> followers) {
		this.followers = followers;
	}

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

	public List<Permissions> getPermission() {
		return permission;
	}

	public void setPermission(List<Permissions> permission) {
		this.permission = permission;
	}
}
