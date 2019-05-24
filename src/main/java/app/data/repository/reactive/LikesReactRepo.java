package app.data.repository.reactive;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import app.data.entity.Likes;

public interface LikesReactRepo extends ReactiveCrudRepository<Likes, String>{

}
