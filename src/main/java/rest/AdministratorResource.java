package rest;

import entities.Profile;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationPath("administrator")
public class AdministratorResource {

	@POST
	@Path("/editProfile")
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
