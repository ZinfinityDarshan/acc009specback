package app.data.repository.reactive;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import app.data.entity.Notification;
import reactor.core.publisher.Flux;

public interface NotificationRepoReact extends ReactiveCrudRepository<Notification, String>{

	@Query("{'userId' : ?0}")
	public Flux<Notification>findAllByUserId(String userId);
}
