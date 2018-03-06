package services;

import entities.Profile;

public interface AuthenticationService {
	String authenticate(String email, String password) throws Exception;
	void deleteToken(Profile profile);
	Profile getProfileByToken(String token);
}
