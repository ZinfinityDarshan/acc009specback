package app.http.model.responses;

import com.fasterxml.jackson.annotation.JsonFormat;

import app.data.entity.Comments;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data@AllArgsConstructor@NoArgsConstructor@SuperBuilder@JsonFormat
public class PostCommentForPostResponse  extends HttpStandardResponse{

	private Comments comments;
}
