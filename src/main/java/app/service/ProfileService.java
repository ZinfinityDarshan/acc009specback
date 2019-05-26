package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import app.data.repository.reactive.FollowersRepoReact;
import app.data.repository.reactive.FollowingRepoReact;

@Service
public class ProfileService {
	
	@Autowired private FollowersRepoReact followerrepo;
	@Autowired private FollowingRepoReact followingrepo;

	public boolean followUser(String follower, String following) {
		
		return true;
	}

	
}
