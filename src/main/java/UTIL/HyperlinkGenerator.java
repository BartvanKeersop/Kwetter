package UTIL;

import rest.KweetResource;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

public class HyperlinkGenerator {

	@Context
	UriInfo uriInfo;

	private void x(){
		build(this.getClass());
	}

	public void build(Class c){
		UriBuilder builder = UriBuilder.fromResource(KweetResource.class);
		builder.host((uriInfo.getBaseUri()).toString());
		builder.path(KweetResource.class, "getKweet");
	}

}
