package app.data.repository.reactive;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import app.data.entity.Followers;

public interface FollowersRepoReact extends ReactiveCrudRepository<Followers, String>{

}
