package app.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="subjectconstant")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectConstant {

	@Id private String id;
	
	private String subjectname;
}
