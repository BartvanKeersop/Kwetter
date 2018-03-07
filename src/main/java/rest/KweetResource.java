package rest;

import entities.Kweet;
import entities.Profile;
import services.KweetService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/Kweet")
public class KweetResource {

	@EJB
	KweetService kweetService;

	@GET
	@Path("/getFeedKweets")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFeedKweets(){
		try{
			Profile profile = new Profile(); //TODO: Get profile out of session
			return Response.ok(
					kweetService.getMyFeedKweets(profile)).build();
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
			Profile profile = new Profile(); //TODO: Get profile out of session
			return Response.ok(
					kweetService.getMyLast10Kweets()).build();
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
			kweetService.createKweet(kweet);
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
			kweetService.deleteKweet(kweet);
			return Response.ok().build();
		}
		catch(Exception e){
			return Response.serverError().build();
		}
	}
}
