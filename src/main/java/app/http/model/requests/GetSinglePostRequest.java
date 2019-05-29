package app.http.model.requests;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@Builder@AllArgsConstructor@NoArgsConstructor@JsonFormat
public class GetSinglePostRequest {

	private String requester;
	private String postId;
}
