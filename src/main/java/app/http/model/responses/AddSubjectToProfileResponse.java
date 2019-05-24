package app.http.model.responses;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonFormat
@EqualsAndHashCode(callSuper=false)
public class AddSubjectToProfileResponse extends HttpStandardResponse{

	private List<String> subjects;
}
