package dto;

import java.io.Serializable;

public class LoginDto implements Serializable {

	long id;
	String username;
	String avatar;
	String token;

	public LoginDto(){}

	public LoginDto(long id, String username, String avatar, String token) {
		this.id = id;
		this.username = username;
		this.avatar = avatar;
		this.token = token;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
