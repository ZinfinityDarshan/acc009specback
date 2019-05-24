package app.data.repository.reactive;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import app.data.entity.Post;

public interface PostRepoReact extends ReactiveCrudRepository<Post, String>{

}
