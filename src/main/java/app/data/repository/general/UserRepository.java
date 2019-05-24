package app.data.repository.general;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import app.data.entity.User;


public interface UserRepository extends MongoRepository<User, String> {

  @Query("{'username':?0}")
  User findByUsername(String username);

  @Query(value="{'username':?0}", delete=true)
  void deleteByUsername(String username);
  
  @Query("{'email':?0}")
  User findByEmail(String email);

}
