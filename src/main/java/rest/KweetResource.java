package rest;

import entities.Kweet;
import filters.AuthenticationFilter.IAuthenticatedUser;
import security.AuthenticatedUser;
import services.KweetService;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/Kweet")
public class KweetResource {

	@Inject
	@IAuthenticatedUser
	AuthenticatedUser authenticatedUser;

	@EJB
	KweetService kweetService;

	@GET
	@Path("/getFeedKweets")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFeedKweets(){
		try{
			return Response.ok(
					kweetService.getMyFeedKweets(authenticatedUser.getId())).build();
		}
		catch(Exception e){
			return Response.serverError().build();
		}
	}

	@GET
	@Path("/getMyLast10Kweets")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMyLast10Kweets(){
		try{
			return Response.ok(
					kweetService.getMyLast10Kweets(authenticatedUser.getId())).build();
		}
		catch(Exception e){
			return Response.serverError().build();
		}
	}

	@POST
	@Path("/createKweet")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createKweet(Kweet kweet){
		try{
			kweetService.createKweet(authenticatedUser.getId(), kweet);
			return Response.ok().build();
		}
		catch(Exception e){
			return Response.serverError().build();
		}
	}

	@POST
	@Path("/deleteKweet")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteKweet(Kweet kweet){
		try{
			kweetService.deleteKweet(authenticatedUser.getId(), kweet, authenticatedUser.getPermissions());
			return Response.ok().build();
		}
		catch(Exception e){
			return Response.serverError().build();
		}
	}
}
