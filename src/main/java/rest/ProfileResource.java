package rest;

import enums.Roles;
import services.ProfileService;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/profile")
public class ProfileResource {

	@EJB
	ProfileService profileService;

	@GET
	@Path("/{param}")
	public Response printMessage(@PathParam("param") String msg) {
		String result;
		if (profileService != null) {
			profileService.createProfile();
			result = "succes";
		}
		else{
			//profileService.createProfile();
			result = "failure";
		}
		return Response.status(200).entity(result).build();
	}
}
