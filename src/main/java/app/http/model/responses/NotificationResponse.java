package app.http.model.responses;

import java.util.List;

import app.data.entity.Notification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode
public class NotificationResponse extends HttpStandardResponse{

	private List<Notification> notifications; 
}
