package app.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("secure/streaming")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StreamingController {

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
}
