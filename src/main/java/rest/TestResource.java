package rest;

import datagenerator.IDataGenerator;
import dto.TestDto;
import filters.AuthenticationFilter.IAuthenticatedUser;
import org.jboss.logging.Logger;
import security.AuthenticatedUser;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.net.URI;

@Path("test")
public class TestResource {

	@Context
	UriInfo uriInfo;

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

	@GET
	@Path("/testHateAOS")
	@Produces(MediaType.APPLICATION_JSON)
	public Response testHateAOS(){
		try{
			//Get own link
			//Link testDtoLink = Link.fromUri(uriInfo.getAbsolutePath()).rel("self").build();

			//Get link from any resource
			UriBuilder builder = UriBuilder.fromResource(TestResource.class);
			builder.host((uriInfo.getBaseUri()).toString());
			builder.path(TestResource.class, "testHateAOS");
			Link link = Link.fromUri(builder.build()).rel("self").build();

			TestDto testDto = new TestDto();
			testDto.getLinks().add(link);
			return Response.ok(testDto).build();
		}
		catch(Exception e){
			return Response.serverError().build();
		}
	}


}
