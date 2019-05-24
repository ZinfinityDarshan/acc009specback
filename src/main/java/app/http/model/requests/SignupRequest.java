package app.http.model.requests;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@JsonFormat
public class SignupRequest {

	  private String email;

	  private String username;

	  private String phoneno;

	  private String password;
	  
}
