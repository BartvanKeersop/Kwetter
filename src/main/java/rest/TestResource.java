package rest;

import filters.AuthenticationFilter.IAuthenticatedUser;
import model.AuthenticatedUser;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("test")
public class TestResource {

	@Inject
	@IAuthenticatedUser
	AuthenticatedUser authenticatedUser;

	@GET
	@Path("/testProfileProducer")
	@Produces(MediaType.APPLICATION_JSON)
	public Response testProfileProducer(){
		try{
			return Response.ok(authenticatedUser).build();
		}
		catch(Exception e){
			return Response.serverError().build();
		}
	}
}
