package app.data.repository.reactive;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import app.data.entity.Profile;
import reactor.core.publisher.Mono;

public interface ProfileRepoReact extends ReactiveCrudRepository<Profile, String>{

	@Query("{'userId': ?0}")
	public Mono<Profile> getByUserId(String userId);
}
