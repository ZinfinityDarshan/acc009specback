package app.data.repository.reactive;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import app.data.entity.Subject;

public interface SubjectReactRepo extends ReactiveCrudRepository<Subject, String>{

}
