package app.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="contact")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserContact {

	@Id
	private String id;
	private String userContact_id;
	
	//connects user with user.id
	@Indexed(unique=true, sparse=true)
	private String userId;
	private String mobile;
	private String altMobile;
	private String email;
	private String flat;
	private String street;
	private String colony;
	private String city;
	private String state;
	private String pincode;
	private String del;
	
	//if needed to validate the address
	private boolean valid;
}
