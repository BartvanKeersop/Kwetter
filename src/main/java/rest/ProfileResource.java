package rest;

import dto.ProfileDto;
import entities.Profile;
import filters.AuthenticationFilter.IAuthenticatedUser;
import org.jboss.logging.Logger;
import security.AuthenticatedUser;
import services.ProfileService;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/profile")
public class ProfileResource {

	@Context
	UriInfo uriInfo;


	@EJB
	ProfileService profileService;

	@Inject
	@IAuthenticatedUser
	AuthenticatedUser authenticatedUser;

	@GET
	@Path("/getProfile/{profileId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProfile(@PathParam("profileId") long profileId){
		try{
			Logger console = Logger.getLogger("CONSOLE");
			console.info("----LOGGING TEST-----");
			return Response.ok(
					profileService.getProfile(profileId)).build();
		}
		catch(Exception e){
			return Response.serverError().build();
		}
	}

	@POST
	@Path("/editProfile")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateProfile(ProfileDto profileDto){
		try{
			profileService.updateProfile(authenticatedUser.getId(), profileDto);
			return Response.ok().build();
		}
		catch(Exception e){
			return Response.serverError().build();
		}
	}

	@POST
	@Path("/followProfile")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response followProfile(Profile profileToFollow){
		try{
			profileService.followProfile(authenticatedUser.getId(), profileToFollow.getId());
			return Response.ok().build();
		}
		catch(Exception e){
			return Response.serverError().build();
		}
	}

	@POST
	@Path("/createProfile")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createProfile(Profile profile){
		try{
			profileService.createProfile(profile);
			return Response.ok().build();
		}
		catch(Exception e){
			return Response.serverError().build();
		}
	}

	@GET
	@Path("/getProfiles/{profileName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProfiles(@PathParam("profileName") String profileName){
		try{
			return Response.ok(
					profileService.getProfiles(profileName)).build();
		}
		catch(Exception e){
			return Response.serverError().build();
		}
	}

	@GET
	@Path("/getFollowers/{profileId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFollowers(@PathParam("profileId") long profileId){
		try{
			return Response.ok(
					profileService.getFollowers(profileId)).build();
		}
		catch(Exception e){
			return Response.serverError().build();
		}
	}

	@GET
	@Path("/getFollowing/{profileId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFollowing(@PathParam("profileId") long profileId){
		try{
			return Response.ok(profileService.getFollowing(profileId)).build();

		}
		catch(Exception e){
			return Response.serverError().build();
		}
	}
}
