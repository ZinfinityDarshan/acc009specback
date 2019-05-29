package app.http.model.responses;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import app.generic.model.ShortProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data@SuperBuilder@AllArgsConstructor@NoArgsConstructor@JsonFormat
public class ListOfProfilesLikedPostResponse extends HttpStandardResponse{

	private List<ShortProfile> profiles;
}
