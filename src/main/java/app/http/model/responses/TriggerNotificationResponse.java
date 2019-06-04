package app.http.model.responses;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data@AllArgsConstructor@NoArgsConstructor@JsonFormat@SuperBuilder
public class TriggerNotificationResponse extends HttpStandardResponse{
	private boolean done;
}
