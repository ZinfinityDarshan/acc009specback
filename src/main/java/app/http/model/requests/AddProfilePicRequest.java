package app.http.model.requests;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor@Builder@JsonFormat
public class AddProfilePicRequest {

	private String userId;
	private String picurl;
}
