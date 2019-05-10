package app.data.entity;

import java.util.List;
import javax.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import app.generic.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document(collection="user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  private String id;

  @Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
  private String username;

  private String email;

  @Size(min = 8, message = "Minimum password length: 8 characters")
  private String password;

  List<Role> roles;

}

