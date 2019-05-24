package app.data.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="post")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
	
	@Id private String id;
	
	private String postId;
	
	//connecting user.id
	@Indexed
	private String user_Id;
	
	private String title;
	private String dscp;
	
	//either img, vid or art
	private String type;
	
	//Below Fields will connect Firestore to store images and videos
	//GCP/Firestore Connect
	private List<String> img;
	private List<String> vid;
	
	//connects list of SubjectConstant.subjectname
	private List<String> tags;
	private String articleId;
	private String createdBy;
	private LocalDateTime createdOn;
	private String updatedBy;
	private LocalDateTime updatedOn;
	@Indexed
	private String likesCount;
	private String commentsCount;
	
	//connecting Likes.id
	private String likes_id;
	//connecting Comments.id
	private String comments_id;
	private String recent;

}
