package rest;

import UTIL.HyperlinkGenerator;
import dto.ProfileDto;
import entities.Profile;
import filters.AuthenticationFilter.IAuthenticatedUser;
import model.Link;
import security.AuthenticatedUser;
import services.ProfileService;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.List;

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
			System.out.println(profileId);
			ProfileDto profileDto = profileService.getProfile(profileId);
			List<Link> links = new ArrayList<>();

			//Link to self
			String link = "/getProfile/" + Long.toString(profileId);
			Link selfLink = new Link(HyperlinkGenerator.build(link), "self");
			links.add(selfLink);

			//Link to kweets
			link = "/getKweetsByProfileId/" + Long.toString(profileId);
			Link kweetsLink = new Link(HyperlinkGenerator.build(link), "kweets");
			links.add(kweetsLink);

			profileDto.setLinks(links);

			return Response.ok(
					profileDto).build();
		}
		catch(Exception e){
			e.printStackTrace();
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
