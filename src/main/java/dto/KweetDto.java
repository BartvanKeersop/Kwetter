package dto;

import java.util.Date;
import java.util.List;

public class KweetDto {
	private long id;
	private String text;
	private Date creationDate;
	private ProfileDto owner;
	private List<String> hashtags;
	private List<ProfileDto> mentions;
	private List<ProfileDto> likedBy;
}
