package app.data.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comments {

	@Id private String id;
	//User.id
	private String commentedBy;
	private String commentedByusername;
	private String commentedByProfilePicUrl;
	private String comment;
	//Post.id
	@Indexed
	private String postID;
	private LocalDateTime commentedOn;

}
