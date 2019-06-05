package app.http.model.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor@Builder
public class LikePostRequest {

	private String postId;
	private String userId;
	private String requester;
	
}
