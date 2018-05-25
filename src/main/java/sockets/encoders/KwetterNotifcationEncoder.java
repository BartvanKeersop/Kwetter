package sockets.encoders;

import sockets.messages.KwetterNotification;
import javax.json.stream.JsonGenerator;
import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.io.StringWriter;

public class KwetterNotifcationEncoder implements Encoder.Text<KwetterNotification>{

	@Override
	public void init(EndpointConfig ec) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public String encode(KwetterNotification kwetterNotification) throws EncodeException {
		StringWriter swriter = new StringWriter();
		try (JsonGenerator jsonGen = Json.createGenerator(swriter)) {
			jsonGen.writeStartObject()
					.write(kwetterNotification.getText())
					.writeEnd();
		}
		return swriter.toString();
	}
}
