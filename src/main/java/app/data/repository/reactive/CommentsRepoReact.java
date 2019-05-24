package app.data.repository.reactive;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import app.data.entity.Comments;

public interface CommentsRepoReact extends ReactiveCrudRepository<Comments, String>{

}
