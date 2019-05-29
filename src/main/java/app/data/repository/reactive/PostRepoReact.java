package app.data.repository.reactive;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import app.data.entity.Post;
import app.data.entity.RecentPost;
import app.data.entity.TrendingPost;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PostRepoReact extends ReactiveCrudRepository<Post, String>{

	@Query("{'postId': ?0}")
	public Mono<Post> findByPostId(String postId);
	
	@Query("{'user_Id': ?0}")
	public Flux<Post> findAllByUserId(String userId);
}
