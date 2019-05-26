package app.http.model.responses;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@JsonFormat
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class HttpStandardResponse {
	
	private String userId;
	private String errorCode;
	private String errorMessage;
	private boolean status;
}
