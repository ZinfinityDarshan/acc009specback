package app.http.model.responses;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data@AllArgsConstructor@NoArgsConstructor@JsonFormat@SuperBuilder@EqualsAndHashCode(callSuper=false)
public class AddProfilePicResponse extends HttpStandardResponse{

	private String userId;
}
