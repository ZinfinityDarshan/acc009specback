package app.data.repository.reactive;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import app.data.entity.Notification;

public interface NotificationRepoReact extends ReactiveCrudRepository<Notification, String>{

}
