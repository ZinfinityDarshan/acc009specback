package app.data.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="subject")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subject {

	@Id private String id;
	
	private String userId;
	private List<String> subjects;
}
