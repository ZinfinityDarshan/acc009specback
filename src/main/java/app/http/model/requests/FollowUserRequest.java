package app.http.model.requests;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;

@Data
@JsonFormat
@Builder
public class FollowUserRequest {

	//both are User.userId
	private String following;
	private String follower;
}
