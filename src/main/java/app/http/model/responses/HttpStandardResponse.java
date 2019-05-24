package app.http.model.responses;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonFormat
@AllArgsConstructor
@NoArgsConstructor
public class HttpStandardResponse {
	
	private String userId;
	private String errorCode;
	private String errorMessage;
	private boolean status;
}
