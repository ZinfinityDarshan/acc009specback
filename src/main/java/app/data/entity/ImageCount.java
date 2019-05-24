package app.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "ImageCount")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageCount {

	@Id private String id;
	private String name;
	private String previous;
}
