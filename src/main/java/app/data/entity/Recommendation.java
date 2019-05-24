package app.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="recommendation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recommendation {

	@Id
	private String id;
	private String recommendation_id;
	
	//connects user
	@Indexed(unique=true, sparse=true)
	private String userId;
	private String comment;
	
}
