package app.http.model.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data@AllArgsConstructor@NoArgsConstructor@SuperBuilder@EqualsAndHashCode(callSuper=false)
public class LikePostResponse extends HttpStandardResponse{

	private String requester;
	private String likecount;
}
