package rest;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.common.TextFormat;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.StreamingOutput;
import java.io.OutputStreamWriter;
import java.io.Writer;

@Path("/metrics")
public class MetricsResource {

	@GET
	public StreamingOutput metrics(){
		return output -> {
			try (Writer writer = new OutputStreamWriter(output)) {
				TextFormat.write004(writer, CollectorRegistry.defaultRegistry.metricFamilySamples());
			}
		};
	}
}
