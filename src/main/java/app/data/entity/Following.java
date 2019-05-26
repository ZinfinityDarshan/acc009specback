package app.data.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="following")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Following {

	@Id private String id;
	@Indexed
	private String userId;
	private List<String> following;
}
