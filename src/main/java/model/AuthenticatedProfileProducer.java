package model;

import filters.AuthenticationFilter.IAuthenticatedUser;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;

@RequestScoped
public class AuthenticatedProfileProducer{

	@Produces
	@IAuthenticatedUser
	private model.AuthenticatedUser authenticatedUser;

	public void handleAuthenticationEvent(@Observes @IAuthenticatedUser AuthenticatedUser authenticatedUser) {
		this.authenticatedUser = authenticatedUser;
	}
}
