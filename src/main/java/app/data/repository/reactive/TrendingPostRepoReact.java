package app.data.repository.reactive;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import app.data.entity.RecentPost;
import app.data.entity.TrendingPost;
import reactor.core.publisher.Mono;

public interface TrendingPostRepoReact extends ReactiveCrudRepository<TrendingPost, String>{

	@Query("{'postId': ?0}")
	public Mono<TrendingPost> findByPostId(String postId);
}
