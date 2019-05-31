package app.data.repository.reactive;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import app.data.entity.Subject;
import reactor.core.publisher.Mono;

public interface SubjectReactRepo extends ReactiveCrudRepository<Subject, String>{

	@Query("{'userId':?0}")
	public Mono<Subject> getByUserId(String userId);
}
