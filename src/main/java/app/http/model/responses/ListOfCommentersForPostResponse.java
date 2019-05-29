package app.http.model.responses;

import java.util.List;

import app.generic.model.ShortProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data@AllArgsConstructor@NoArgsConstructor@SuperBuilder@EqualsAndHashCode(callSuper=false)
public class ListOfCommentersForPostResponse extends HttpStandardResponse{

	private List<ShortProfile> profiles; 
}
