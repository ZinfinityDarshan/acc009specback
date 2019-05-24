package app.data.repository.reactive;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import app.data.entity.UserContact;

public interface UserContactRepoReact extends ReactiveCrudRepository<UserContact, String>{

}
