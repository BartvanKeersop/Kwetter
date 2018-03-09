package rest;

import entities.Profile;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationPath("moderator")
public class ModeratorResource {

	@GET
	@Path("/getProfilesWithRoles/{profileName}}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProfilesWithRoles(@PathParam("profileName") String name){
		try{
			return Response.noContent().build();
		}
		catch(Exception e){
			return Response.serverError().build();
		}
	}

	@POST
	@Path("/deleteKweet")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteKweet(Profile profile){
		try{
			return Response.noContent().build();
		}
		catch(Exception e){
			return Response.serverError().build();
		}
	}
}
