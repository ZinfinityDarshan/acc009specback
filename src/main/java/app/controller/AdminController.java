package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.data.entity.Profile;
import app.data.entity.User;
import app.data.repository.general.UserRepository;
import app.data.repository.reactive.ProfileRepoReact;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/admin")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminController {

	@Autowired private ProfileRepoReact profilerepo;
	@Autowired private UserRepository userrepo;
	
	@GetMapping("getActorProfiles") public Flux<Profile>getActorProfiles() {
		return profilerepo.findAll();
	}
	
	@GetMapping("getUsers") public List<User> getUsers() {
		return userrepo.findAll();
	}
}
