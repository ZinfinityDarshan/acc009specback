package app.generic.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor@JsonFormat@Builder
public class ShortProfile {

	private String userId;
	private String username;
	private String profilepicurl;
}
