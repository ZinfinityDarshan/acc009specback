package app.data.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="followers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Followers {

	
	@Id private String id;
	//User.id
	private String userId;
	private List<String> followers;
}
