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
import javax.ws.rs.core.UriBuilder;

@Path("/kweet")
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
	@Path("/getMyKweets")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMyKweets(){
		try{
			return Response.ok(
					kweetService.getMyKweets(authenticatedUser.getId())).build();
		}
		catch(Exception e){
			return Response.serverError().build();
		}
	}

	@GET
	@Path("/getAllKweets")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllKweets(){
		try{
			return Response.ok(
					kweetService.getAllKweets()).build();
		}
		catch(Exception e){
			return Response.serverError().build();
		}
	}

	@GET
	@Path("/getKweetsByProfileId/{profileId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProfile(@PathParam("profileId") long profileId){
		try{
			return Response.ok(
					kweetService.getMyKweets(profileId)).build();
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
			System.out.println("*****************************AUTHENTICATED USER ID" + Long.toString(authenticatedUser.getId()));
			kweetService.createKweet(authenticatedUser.getId(), kweet);
			return Response.ok().build();
		}
		catch(Exception e){
			return Response.serverError().build();
		}
	}

	@POST
	@Path("/likeKweet")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response likeKweet(Kweet kweet){
		try{
			kweetService.likeKweet(authenticatedUser.getId(), kweet.getId());
			return Response.ok().build();
		}
		catch(Exception e){
			return Response.serverError().build();
		}
	}

	@POST
	@Path("/unlikeKweet")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response unlikeKweet(Kweet kweet){
		try{
			kweetService.unlikeKweet(authenticatedUser.getId(), kweet.getId());
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
			//kweetService.deleteKweet(authenticatedUser.getId(), kweet, authenticatedUser.getPermissions());
			return Response.ok().build();
		}
		catch(Exception e){
			return Response.serverError().build();
		}
	}
}
