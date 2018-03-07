package rest;

import entities.Profile;
import filters.AuthenticationFilter.AuthenticatedProfile;
import services.ProfileService;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/profile")
public class ProfileResource {

	@EJB
	ProfileService profileService;

	@Inject
	@AuthenticatedProfile
	Profile authenticatedProfile;

	@GET
	@Path("/getProfile/{profileId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProfile(@PathParam("profileId") long profileId){
		try{
			return Response.ok(
					profileService.getProfile(profileId)).build();
		}
		catch(Exception e){
			return Response.serverError().build();
		}
	}

	@POST
	@Path("/updateUsername/{profileId},{newUsername}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateUsername(@PathParam("profileId") long profileId,
								   @PathParam("newUsername") String newUsername){
		try{
			profileService.updateUsername(profileId, newUsername);
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
			profileService.followProfile(authenticatedProfile, profileToFollow);
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
	@Path("/getFollowers/{profileId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFollowing(@PathParam("profileId") long profileId){
		try{
			return Response.ok(
					profileService.getFollowing(profileId)).build();
		}
		catch(Exception e){
			return Response.serverError().build();
		}
	}
}
