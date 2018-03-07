package model;

import entities.Profile;
import filters.AuthenticationFilter.AuthenticatedProfile;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;

@RequestScoped
public class AuthenticatedProfileProducer{

	@Produces
	@AuthenticatedProfile
	private Profile authenticatedProfile;

	public void handleAuthenticationEvent(@Observes @AuthenticatedProfile Profile profile) {
		this.authenticatedProfile = profile;
	}
}
