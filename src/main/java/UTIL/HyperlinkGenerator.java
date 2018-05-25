package UTIL;

import rest.KweetResource;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.util.List;

public class HyperlinkGenerator {

	private static final String host = "http:/localhost:8080/Kwetter/api";

	public static String build(String link){
		StringBuilder builder = new StringBuilder();
		builder.append(host);
		builder.append(link);
		return builder.toString();
	}

}
