package app.data.repository.reactive;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import app.data.entity.Activity;

public interface ActivityRepoReact extends ReactiveCrudRepository<Activity, String>{

}
