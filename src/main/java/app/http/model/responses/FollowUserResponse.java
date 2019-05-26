package app.http.model.responses;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper=false)
public class FollowUserResponse extends HttpStandardResponse{

	private String follower;
	private String following;
}
