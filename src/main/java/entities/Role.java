package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Role implements Serializable{

	@Id
	@Column(name = "role_name")
	private String name;

	@ManyToMany
	@JoinTable(name= "profile_role",
				joinColumns = @JoinColumn(name = "role_name", referencedColumnName = "role_name"),
				inverseJoinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "profile_id"))
	private List<Profile> profiles;

	@Column(name = "role_group")
	private String roleGroup;

	public Role() {}

	public String getProfileName() {
		return name;
	}

	public void setProfileName(String name) {
		this.name = name;
	}

	public List<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}
}
