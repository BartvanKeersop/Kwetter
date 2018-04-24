package dto;

import javax.ws.rs.core.Link;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TestDto implements Serializable {

	private String text;
	private List<Link> links;

	public TestDto(){
		text = "hoi";
		links = new ArrayList<>();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

}
