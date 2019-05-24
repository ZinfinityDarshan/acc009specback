package app.data.repository.reactive;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import app.data.entity.IDConfiguration;
import reactor.core.publisher.Mono;

public interface IDConfigurationRepoReact extends ReactiveCrudRepository<IDConfiguration, String>{

	@Query("{'document': ?0}")
	public Mono<IDConfiguration> findoneByDocumentName(String DocumentName);
	
	@Query("{'document': ?0}")
	public Mono<IDConfiguration> findByDocumentName(String DocumentName);
}
