package app.http.model.responses;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@JsonFormat
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PostAddedResponse extends HttpStandardResponse{

	private String postId;
	private String userId;
	
}
