package dto;

import entities.Kweet;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class KweetDto {
	private long id;
	private String text;
	private LocalDateTime creationDate;
	private ProfileDto owner;
	private List<String> hashtags;
	private List<ProfileDto> mentions;
	private List<ProfileDto> likedBy;
	private List<String> links;

	public KweetDto(Kweet kweet){
		this.id = kweet.getId();
		this.text = kweet.getText();
		this.creationDate = kweet.getCreationDate();
	}

	public long getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public ProfileDto getOwner() {
		return owner;
	}

	public List<String> getHashtags() {
		return hashtags;
	}

	public List<ProfileDto> getMentions() {
		return mentions;
	}

	public List<ProfileDto> getLikedBy() {
		return likedBy;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public void setOwner(ProfileDto owner) {
		this.owner = owner;
	}

	public void setHashtags(List<String> hashtags) {
		this.hashtags = hashtags;
	}

	public void setMentions(List<ProfileDto> mentions) {
		this.mentions = mentions;
	}

	public void setLikedBy(List<ProfileDto> likedBy) {
		this.likedBy = likedBy;
	}

	public List<String> getLinks() {
		return links;
	}

	public void setLinks(List<String> links) {
		this.links = links;
	}
}
