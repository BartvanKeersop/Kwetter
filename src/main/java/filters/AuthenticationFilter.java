package filters;

import entities.Profile;
import enums.Permissions;
import model.AuthenticatedUser;
import services.AuthenticationService;
import javax.annotation.Priority;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Qualifier;
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
import java.util.ArrayList;
import java.util.List;


@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

	private static final String REALM = "example";
	private static final String AUTHENTICATION_SCHEME = "Bearer";

	@Qualifier
	@Retention(value = RetentionPolicy.RUNTIME)
	@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
	public @interface IAuthenticatedUser { }

	@Inject
	@IAuthenticatedUser
	Event<AuthenticatedUser> userAuthenticatedEvent;

	@Inject
	AuthenticationService authenticationService;

	@Override
	public void filter(ContainerRequestContext containerRequestContext) throws IOException {

		String authorizationHeader =
				containerRequestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

		//Add this if request MUST have an authorization token.
		/**
		if (!isTokenBasedAuthentication(authorizationHeader)) {
			abortWithUnauthorized(containerRequestContext);
			return;
		}
		 **/

		AuthenticatedUser authenticatedUser;

		if (authorizationHeader != null){
		 String token = authorizationHeader
				.substring(AUTHENTICATION_SCHEME.length()).trim();
			authenticatedUser = createAuthenticatedUser(validateToken(token));
		}
		else{
			authenticatedUser = createAuthenticatedUser(null);
		}

		userAuthenticatedEvent.fire(authenticatedUser);
	}

	private boolean isTokenBasedAuthentication(String authorizationHeader) {
		return authorizationHeader != null && authorizationHeader.toLowerCase()
				.startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
	}

	private void abortWithUnauthorized(ContainerRequestContext requestContext, Exception e) {
		requestContext.abortWith(
				Response.status(Response.Status.UNAUTHORIZED)
						.header(HttpHeaders.WWW_AUTHENTICATE,
								AUTHENTICATION_SCHEME + " realm=\"" + REALM + "\"")
						.build());
		requestContext.abortWith(Response.ok(e.toString()).build());
	}

	private Profile validateToken(String token) {
		return authenticationService.getProfileByToken(token);
	}

	private AuthenticatedUser createAuthenticatedUser(Profile profile){
		AuthenticatedUser authenticatedUser = new AuthenticatedUser();

		if (profile == null){
			authenticatedUser.setId(-1);
			List<Permissions> permissions = new ArrayList<>();
			permissions.add(Permissions.GUEST);
			authenticatedUser.setPermissions(permissions);
		}
		else{
			authenticatedUser.setId(profile.getId());
			authenticatedUser.setPermissions(profile.getPermission());
		}

		return authenticatedUser;
	}
}
