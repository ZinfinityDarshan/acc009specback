package app.http.model.responses;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProfileResponse extends HttpStandardResponse{

	private String id;
	private String profile_id;
	private String username;
	private String f_name;
	private String l_name;
	private String createdBy;
	private LocalDateTime createdOn;
	private String updatedBy;
	private LocalDateTime updatedDate;
	
	//connects user with User.id
	@Indexed(unique=true, sparse=true)
	private String userId;
	private String bio;
	private List<String> awards;
	private String postCount;
	private String followerCount;
	private String followingcount;
	private String recommendId;
	
	//after uploading URL of profilepic pass that here
	private String profilePicUrl;
	private String DOB;
	
	//needs to select the profession to identify account Artist or Production
	private List<String> professsion;
	
	//connects subject with Subject.Id
	private String subjectId;
	
	// active/inactive
	private String status;
	
	//if deleted == true | not deleted == false
	private boolean del;
	private String followers;
	private String following;
	
	private List<String> notifications;
	
	private List<String> activity;
	
	@Transient
	private boolean followedbool;
}
