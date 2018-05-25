import sockets.encoders.KwetterNotifcationEncoder;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint(
		value = "/socket",
		encoders = {KwetterNotifcationEncoder.class}
)

public class SocketEndpoint {
	private static final Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

	@OnMessage
	public void onMessage(final Session client, final String message) {
		if (message != null) {
			sendAll(message);
		}
	}

	@OnOpen
	public void onOpen(Session peer) {
		System.out.println("---opening connection---");
		peers.add(peer);
	}

	@OnClose
	public void onClose(Session peer) {
		System.out.println("---closing connection---");
		peers.remove(peer);
	}

	@OnError
	public void onError(Throwable t) {
		System.out.println("---error---");
		//error
	}

	private void sendAll(Object answer) {
		System.out.println("---send all---");
		System.out.println(answer.toString());
		peers.stream().forEach((peer) -> {
			sendMessage(peer, answer);
		});
	}

	private void sendMessage(Session peer, Object send) {
		try {
			if (peer.isOpen()) {
				peer.getBasicRemote().sendObject(send);
			}
		} catch (Exception ex) {
		}
	}

}
