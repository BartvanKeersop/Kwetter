package services;

import dto.LoginDto;
import entities.Profile;

public interface AuthenticationService {
	LoginDto authenticate(String email, String password) throws Exception;
	void deleteToken(Profile profile);
	Profile getProfileByToken(String token);
}
