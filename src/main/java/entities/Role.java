package entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NamedQueries({
		@NamedQuery(name="Role.getRoles", query="SELECT r FROM Role r"),
		})
public class Role implements Serializable{

	@Id
	@Column(name = "role_name")
	private String name;

	@ManyToMany(cascade = CascadeType.ALL)
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
