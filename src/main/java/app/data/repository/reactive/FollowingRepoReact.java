package app.data.repository.reactive;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import app.data.entity.Following;

public interface FollowingRepoReact extends ReactiveCrudRepository<Following, String>{

}
