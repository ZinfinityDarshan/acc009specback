package app.data.repository.reactive;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import app.data.entity.ImageCount;
import reactor.core.publisher.Mono;

public interface ImageCountRepoReact extends ReactiveCrudRepository<ImageCount, String>{

	@Query("{'name': ?0}")
	public Mono<ImageCount> findoneByName(String name);
}
