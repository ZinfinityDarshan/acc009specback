package app.data.repository.reactive;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import app.data.entity.RecentPost;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RecentPostReactRepo extends ReactiveCrudRepository<RecentPost, String>{

	@Query("{'postId': ?0}")
	public Mono<RecentPost> findByPostId(String postId);
	
	public Flux<RecentPost> findAll();
	
	@Query("{'user_Id': ?0}")
	public Flux<RecentPost> findAllByUserId(String userId);
}
