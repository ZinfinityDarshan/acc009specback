package app.data.repository.reactive;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import app.data.entity.Comments;
import reactor.core.publisher.Flux;

public interface CommentsRepoReact extends ReactiveCrudRepository<Comments, String>{

	@Query("{'postID': ?0}")
	public Flux<Comments>findAllCommentsByPostId(String postId);
	
}
