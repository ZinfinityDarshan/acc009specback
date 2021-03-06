package app.data.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="trendingposts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrendingPost {

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
	private int likesCount;
	private int commentsCount;
	
	//if the post is approved or not
	private boolean status;
	
	@Transient
	private boolean likedByRequester;
	
	//connecting Likes.id
	private String likes_id;
	//connecting Comments.id
	private String comments_id;
	private String recent;
}
