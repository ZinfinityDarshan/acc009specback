package app.data.repository.reactive;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import app.data.entity.Likes;
import reactor.core.publisher.Mono;

public interface LikesReactRepo extends ReactiveCrudRepository<Likes, String>{

	@Query("{'postId':?0}")
	public Mono<Likes> getLikesByPostId(String postId);
}
