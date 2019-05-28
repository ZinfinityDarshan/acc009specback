package app.data.repository.reactive;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import app.data.entity.Following;
import reactor.core.publisher.Mono;

public interface FollowingRepoReact extends ReactiveCrudRepository<Following, String>{

	@Query("{'userId':?0}")
	public Mono<Following> findByUserId(String userId);
}
