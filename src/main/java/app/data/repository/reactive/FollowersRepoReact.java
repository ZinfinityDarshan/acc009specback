package app.data.repository.reactive;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import app.data.entity.Followers;
import reactor.core.publisher.Mono;

public interface FollowersRepoReact extends ReactiveCrudRepository<Followers, String>{

	@Query("{'userId':?0}")
	public Mono<Followers> findByUserId(String userId);
}
