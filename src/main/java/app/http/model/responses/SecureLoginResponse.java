package app.http.model.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SecureLoginResponse {

	private String username;
	private String token;
	private String errorCode;
	private String errorMessage;
	private boolean status;
}
