package app.data.entity;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="activity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Activity {

	@Id private String id;
	private String activity;
	private LocalDateTime createdOn;
	@Indexed
	private String userId;
}
