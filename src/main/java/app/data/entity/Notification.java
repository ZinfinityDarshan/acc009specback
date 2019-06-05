package app.data.entity;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="notification")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {
	
	@Id private String id;
	private String message;
	private LocalDateTime createdon;
	
	@Indexed
	private String userId;
	
	//user Id of the user who notified 
	private String notifiedBy;

}
