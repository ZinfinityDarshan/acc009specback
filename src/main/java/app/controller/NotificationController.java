package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.data.dao.GenericDAO;
import app.data.entity.Notification;
import app.data.repository.reactive.NotificationRepoReact;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/secure/notification")
@Slf4j
public class NotificationController {
	
	@Autowired NotificationRepoReact notificationrepo;
	@Autowired GenericDAO dao;

	@GetMapping("getNotifications/{userId}")
	public Flux<Notification>getAllNotificationaforUser(@PathVariable String userId){
		//return notificationrepo.findAllByUserId(userId);
		return dao.getNotificationsForUser(userId);
	}
}
