package app.http.model.responses;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import app.generic.model.ShortProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data@NoArgsConstructor@AllArgsConstructor@SuperBuilder@JsonFormat@EqualsAndHashCode(callSuper=false)
public class FollowingProfilesResponse extends HttpStandardResponse{

	private List<ShortProfile> profilelist;

}
