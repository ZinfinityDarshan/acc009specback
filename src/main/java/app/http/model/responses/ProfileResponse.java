package app.http.model.responses;

import lombok.Data;

@Data
public class ProfileResponse {

	private String name;
	private String bio;
	private String userId;
	private String postCount;
	private String followerCount;
	private String followingcount;
	private String profilePicUrl;
}
