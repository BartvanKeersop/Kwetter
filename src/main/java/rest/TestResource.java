package rest;

import entities.Profile;
import filters.AuthenticationFilter.AuthenticatedProfile;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("test")
public class TestResource {

	@Inject
	@AuthenticatedProfile
	Profile authenticatedProfile;

	@GET
	@Path("/testProfileProducer")
	@Produces(MediaType.APPLICATION_JSON)
	public Response testProfileProducer(){
		try{
			return Response.ok(authenticatedProfile).build();
		}
		catch(Exception e){
			return Response.serverError().build();
		}
	}
}
