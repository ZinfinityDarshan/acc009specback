package app;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import app.config.CorsFilter;

@SpringBootApplication
@EnableScheduling
public class StrugglingStarsBackendApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StrugglingStarsBackendApiApplication.class, args);
	}

	  @Bean
	  public ModelMapper modelMapper() {
	    return new ModelMapper();
	  }
	  
}
