package app.http.model.requests;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonFormat
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddSubjectsToProfileRequest {

	private String userId;
	private List<String> subjects;
}
