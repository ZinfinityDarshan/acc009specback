package app.data.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="likes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Likes {

	@Id private String id;
	//User.id
	private List<String> likedBy;
	
	private String likeCount;
	//Post.id
	@Indexed
	private String postId;
}
