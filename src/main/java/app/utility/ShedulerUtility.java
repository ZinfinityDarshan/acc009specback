package app.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.data.repository.reactive.PostRepoReact;
import app.data.repository.reactive.TrendingPostRepoReact;

@Service
public class ShedulerUtility {
	
	@Autowired TrendingPostRepoReact trendingrepo;
	@Autowired PostRepoReact postrepo;
	

	private void TrendingFeedScheduler() {
		
	}
}
