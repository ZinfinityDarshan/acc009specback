package app.data.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import app.data.repository.general.UserRepository;

@Repository
public class CommonDao {

	@Autowired private MongoTemplate mongoTemplate;
	@Autowired private UserRepository repo;
	
	
	public boolean userExistbyUsername(String username) {
		if(repo.findByUsername(username)!= null) {
			return true;
		};
		return false;
	}
}
