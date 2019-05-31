package app.http.model.responses;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import app.data.entity.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data@AllArgsConstructor@NoArgsConstructor@JsonFormat@SuperBuilder
public class TagsForUserResponse extends HttpStandardResponse{

	private List<Subject> tags;
	private String username;
}
