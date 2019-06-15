package app.controller;

import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.data.entity.TrendingPost;
import app.data.repository.reactive.TrendingPostRepoReact;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("secure/streaming")
@Slf4j
public class StreamingController {
	
	@Autowired TrendingPostRepoReact trendingrepo;
	

	/*
	 * getHomeStreaming()
	 * 
	 *	getting the data for home page or feeds
	 *	
	 *
	 *
	 * getTredingScreens()
	 * 	Logic- getting direct data from TrendingPosts table 50 post limits 
	 * 
	 * scheduleTrendingPosts()
	 * scheduling the posts to trending screen 
	 * 
	 */
	@GetMapping("/getTrends")
	public Flux<TrendingPost> getTrendingPots(){
		return Flux.fromStream(trendingrepo.findAll().toStream().sorted(Comparator.comparing(TrendingPost::getUpdatedOn).reversed()));
	}
	
}
