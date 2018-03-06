package rest;

import entities.Profile;
import services.AuthenticationService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/authentication")
public class AuthenticationResource {

	@EJB
	AuthenticationService authenticationService;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response Login(Profile profile) {
		try {
			String token = authenticationService
					.authenticate(profile.getUsername(), profile.getPassword());

			return Response.ok(token).build();

		} catch (Exception e) {
			return Response.status(Response.Status.FORBIDDEN).build();
		}
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response Logout(Profile profile) {
		try {
			authenticationService.deleteToken(
					authenticationService.getProfileByToken(profile.getToken()));
			return Response.ok().build();
		}
		catch (Exception e){
			return Response.serverError().build();
		}
	}
}
