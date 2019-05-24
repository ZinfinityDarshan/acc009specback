package app.http.model.responses;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@JsonFormat
public class EntityAddedResponse {

	private String id;
	private String message;
	private String errorCode;
	private String errorMessage;
	private boolean status;
}
