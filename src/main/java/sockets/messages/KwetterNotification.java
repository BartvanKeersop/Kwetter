package sockets.messages;

public class KwetterNotification {

	private String text;

	public KwetterNotification(String text){
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
