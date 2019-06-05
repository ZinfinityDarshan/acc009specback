package app.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import app.data.dao.GenericDAO;
import app.data.repository.reactive.PostRepoReact;
import app.data.repository.reactive.TrendingPostRepoReact;

@Service
public class ShedulerUtility {
	
	@Autowired TrendingPostRepoReact trendingrepo;
	@Autowired PostRepoReact postrepo;
	@Autowired GenericDAO dao;
	

	@Scheduled(fixedDelay = 3600000)
	private void TrendingFeedScheduler() {
		dao.sortTrendingPosts().subscribe(data ->{
			trendingrepo.save(data).block();
		});
	}
}
