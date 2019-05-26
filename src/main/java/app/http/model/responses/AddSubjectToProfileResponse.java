package app.http.model.responses;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import app.http.model.responses.HttpStandardResponse;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonFormat
@SuperBuilder
public class AddSubjectToProfileResponse extends HttpStandardResponse{

	private List<String> subjects;
}
