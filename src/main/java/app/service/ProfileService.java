package app.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import app.constants.ErrorConstants;
import app.data.entity.Followers;
import app.data.entity.Following;
import app.data.entity.Profile;
import app.data.repository.reactive.FollowersRepoReact;
import app.data.repository.reactive.FollowingRepoReact;
import app.data.repository.reactive.ProfileRepoReact;
import app.generic.model.ShortProfile;
import app.http.model.responses.FollowersProfilesResponse;
import app.http.model.responses.FollowingProfilesResponse;

@Service
public class ProfileService {
	
	@Autowired private FollowersRepoReact followerrepo;
	@Autowired private FollowingRepoReact followingrepo;
	@Autowired private ProfileRepoReact profilerepo;


	public boolean followUser(String follower, String following) {
		boolean status = false;
		try {
		Following f1 = followingrepo.findByUserId(follower).block();
		f1.setFollowing(Arrays.asList(following));
		Profile p1 = profilerepo.getByUserId(follower).block();
		p1.setFollowing(String.valueOf(Integer.parseInt(p1.getFollowingcount())+1));
		profilerepo.save(p1).block();		
		
		Followers f2 = followerrepo.findByUserId(following).block();
		f2.setFollowers(Arrays.asList(follower));
		Profile p2 = profilerepo.getByUserId(following).block();
		p2.setFollowers(String.valueOf(Integer.parseInt(p1.getFollowerCount())+1));
		profilerepo.save(p2).block();
		
		status = true;
		}catch (Exception e) {
			status = false;
		}
		return status;
	}
	
	public FollowersProfilesResponse getFollowersList(String userId) {
		FollowersProfilesResponse res  = FollowersProfilesResponse.builder().build();
		try {
			List<String> followers = followerrepo.findByUserId(userId).block().getFollowers();
			List<ShortProfile> shortprofiles = new ArrayList<ShortProfile>();
			followers.stream().forEach(data -> {
				Profile p1 = profilerepo.getByUserId(data).block();
				ShortProfile s = ShortProfile.builder().userId(p1.getUserId()).
						username(p1.getUsername()).profilepicurl(p1.getProfilePicUrl()).build();
				shortprofiles.add(s);
			});
			res.setProfilelist(shortprofiles);
			res.setStatus(true);
		}catch (Exception e) {
			res.builder().status(false).errorCode(ErrorConstants.InternalError).errorMessage("Internal Error Contact Support").build();
		}
		
		return res;
	}

	public FollowingProfilesResponse getFollowingProfiles(String userId) {
		FollowingProfilesResponse res  =  FollowingProfilesResponse.builder().build();
		
		try {
			List<String> followings = followingrepo.findByUserId(userId).block().getFollowing();
			List<ShortProfile> shortprofiles = new ArrayList<ShortProfile>();
			followings.stream().forEach(data -> {
				Profile p1 = profilerepo.getByUserId(data).block();
				ShortProfile s = ShortProfile.builder().userId(p1.getUserId()).
						username(p1.getUsername()).profilepicurl(p1.getProfilePicUrl()).build();
				shortprofiles.add(s);
			});
			res.setProfilelist(shortprofiles);
			res.setStatus(true);
		}catch(Exception e) {
			res.builder().status(false).errorCode(ErrorConstants.InternalError).errorMessage("Internal Error Contact Support").build();
		}
		return res;
	}
	
}
