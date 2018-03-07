package filters;

import entities.Profile;
import services.AuthenticationService;
import javax.annotation.Priority;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Qualifier;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

	private static final String REALM = "example";
	private static final String AUTHENTICATION_SCHEME = "Bearer";

	@Qualifier
	@Retention(value = RetentionPolicy.RUNTIME)
	@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
	public @interface AuthenticatedProfile { }

	@Inject
	@AuthenticatedProfile
	Event<Profile> profileAuthenticatedEvent;

	@Inject
	AuthenticationService authenticationService;

	@Override
	public void filter(ContainerRequestContext containerRequestContext) throws IOException {

		String authorizationHeader =
				containerRequestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

		if (!isTokenBasedAuthentication(authorizationHeader)) {
			abortWithUnauthorized(containerRequestContext);
			return;
		}

		String token = authorizationHeader
				.substring(AUTHENTICATION_SCHEME.length()).trim();

		try {
			profileAuthenticatedEvent.fire(validateToken(token));

		} catch (Exception e) {
			abortWithUnauthorized(containerRequestContext);
		}
	}

	private boolean isTokenBasedAuthentication(String authorizationHeader) {
		return authorizationHeader != null && authorizationHeader.toLowerCase()
				.startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
	}

	private void abortWithUnauthorized(ContainerRequestContext requestContext) {

		// Abort the filter chain with a 401 status code response
		// The WWW-Authenticate header is sent along with the response
		requestContext.abortWith(
				Response.status(Response.Status.UNAUTHORIZED)
						.header(HttpHeaders.WWW_AUTHENTICATE,
								AUTHENTICATION_SCHEME + " realm=\"" + REALM + "\"")
						.build());
	}

	private Profile validateToken(String token) {
		Profile profile = authenticationService.getProfileByToken(token);
		if (profile == null){
			throw new ForbiddenException();
		}
		else return profile;
	}
}
