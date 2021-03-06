package app.data.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import app.data.entity.Following;
import app.data.entity.Notification;
import app.data.entity.Post;
import app.data.entity.Profile;
import app.data.entity.RecentPost;
import app.data.entity.TrendingPost;
import app.data.repository.reactive.FollowersRepoReact;
import app.data.repository.reactive.FollowingRepoReact;
import app.data.repository.reactive.PostRepoReact;
import app.data.repository.reactive.ProfileRepoReact;
import app.data.repository.reactive.RecentPostReactRepo;
import app.data.repository.reactive.TrendingPostRepoReact;
import reactor.core.publisher.Flux;

@Repository
public class GenericDAO {

	@Autowired MongoTemplate dbtemplate;
	@Autowired TrendingPostRepoReact trendingrepo;
	@Autowired PostRepoReact postrepo;
	@Autowired ProfileRepoReact profilerepo;
	@Autowired FollowingRepoReact followingrepo;
	@Autowired FollowersRepoReact followerrepo;
	@Autowired RecentPostReactRepo recentpostrepo;
	@Autowired ModelMapper mapper;
	
	/*getHomePost(requester<userId>) - query recentposts
	*    //Logic -
	*    check how many users the userId is following from followingList
	*    get first 50 posts from those users 
	*    sort the posts with Stream sort with param LocalDatetime 
	*    check for is requester have liked it 
	*    event stream the same with clilent end 
    */
	
//	public Flux<RecentPost> getHomeScreenPosts(String userId) {
//		List<RecentPost> recentPosts = new ArrayList<RecentPost>();
//		try {
//			List<String> followingpeople = followingrepo.findByUserId(userId).block().getFollowing();
//			List<String> followers = followerrepo.findByUserId(userId).block().getFollowers();
//			
//			followingpeople.parallelStream().forEach(userid ->{
//				Query query = new Query();
//				query.addCriteria(Criteria.where("user_Id").is(userId));
//				query.with(new Sort(Sort.Direction.ASC, "updatedOn"));
//				List<RecentPost> recentPost = dbtemplate.find(query, RecentPost.class);
//			});
//			recentPosts = followingpeople.parallelStream().map(userid -> sortRecentPosts(userid)).co;
//			
//			return Flux.fromStream(recentPosts.stream());
//								
//		}catch(Exception e) {
//			return null;
//		}
//	}
	
//	private List<RecentPost> sortRecentPosts(String userid){
//		Query query = new Query();
//		query.addCriteria(Criteria.where("user_Id").is(userid));
//		query.with(new Sort(Sort.Direction.ASC, "updatedOn"));
//		return dbtemplate.find(query, RecentPost.class);
//	}
	
	public Flux<RecentPost> getRecentPosts(){
		Query q = new Query();
		q.addCriteria(Criteria.where("img").ne(""));
		//q.addCriteria(Criteria.where("img").ne(null));
		q.with(new Sort(Sort.Direction.DESC, "updatedOn"));
		return Flux.fromStream(dbtemplate.find(q, RecentPost.class).stream());
	}
	
	public Flux<Profile> searchForProfile(String match){
		Query q = new Query();
		q.addCriteria(Criteria.where("username").regex(match, "i"));
		q.limit(10);
		return Flux.fromStream(dbtemplate.find(q, Profile.class).stream());
	}
	
	public Flux<TrendingPost> sortTrendingPosts(){
		try
		{
		Query q = new Query();
		List<TrendingPost> trp = new ArrayList<TrendingPost>();
		q.with(new Sort(Sort.Direction.ASC, "likesCount"));
		q.limit(100);
		dbtemplate.find(q, Post.class).parallelStream().filter(value -> value!=null).forEach(post ->{
			trp.add(mapper.map(post, TrendingPost.class));
		});
		trp.removeIf(data -> data == null);
		System.out.println("values"+trp);
		return Flux.fromArray((trp.toArray(new TrendingPost[0])));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public Flux<Notification> getNotificationsForUser(String userId){
		Query q = new Query();
		q.addCriteria(Criteria.where("userId").is(userId));
		q.limit(50);
		q.with(new Sort(Sort.Direction.DESC, "createdon"));
		return Flux.fromStream(dbtemplate.find(q, Notification.class).stream());
	}
	
	public Flux<Post> getPostForUser(String userId){
		Query q = new Query();
		q.addCriteria(Criteria.where("user_Id").is(userId));
		q.with(new Sort(Sort.Direction.DESC, "updatedOn"));
		return Flux.fromStream(dbtemplate.find(q, Post.class).stream());
	}
}
