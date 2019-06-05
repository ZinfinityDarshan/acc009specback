package app.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.data.entity.Notification;
import app.data.entity.Profile;
import app.data.repository.reactive.NotificationRepoReact;
import app.data.repository.reactive.ProfileRepoReact;

@Service
public class NotificationService {

	@Autowired NotificationRepoReact notificationrepo;
	@Autowired ProfileRepoReact profilerepo;
	
	public boolean triggerNotification(Notification notification) {
		try {
			Notification n = notificationrepo.save(notification).block();
			Profile p = profilerepo.getByUserId(notification.getUserId()).block();
			p.setNotifications(Arrays.asList(n.getId()));
			profilerepo.save(p).block();
			return true;
		}catch(Exception e) {
			return false;
		}
	}
}
