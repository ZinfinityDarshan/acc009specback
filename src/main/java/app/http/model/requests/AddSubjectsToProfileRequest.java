package app.http.model.requests;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@JsonFormat
public class AddSubjectsToProfileRequest {

	private String userId;
	private List<String> subjects;
}
