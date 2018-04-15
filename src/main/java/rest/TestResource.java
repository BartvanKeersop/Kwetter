package rest;

import datagenerator.IDataGenerator;
import filters.AuthenticationFilter.IAuthenticatedUser;
import security.AuthenticatedUser;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("test")
public class TestResource {

	@Inject
	@IAuthenticatedUser
	AuthenticatedUser authenticatedUser;

	@Inject
	IDataGenerator dataGenerator;

	@GET
	@Path("/generate")
	public Response get(){
		try{
			dataGenerator.generateData();
			return Response.ok().build();
		}
		catch(Exception e){
			return Response.serverError().build();
		}
	}
}
