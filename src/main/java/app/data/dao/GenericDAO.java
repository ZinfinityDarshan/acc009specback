package app.data.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import app.data.entity.Post;
import app.data.entity.Profile;
import app.data.entity.RecentPost;
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
	
}
