package app.http.model.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor@JsonFormat@Builder
public class TrggerNotificationRequest {

	private String requester;
	private String notifier;
	private String message;
}
