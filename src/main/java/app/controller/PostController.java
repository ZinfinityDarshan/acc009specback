package app.controller;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.constants.DataBaseConstants;
import app.constants.ErrorConstants;
import app.data.entity.Comments;
import app.data.entity.Likes;
import app.data.entity.Post;
import app.data.entity.Profile;
import app.data.entity.RecentPost;
import app.data.entity.User;
import app.data.repository.reactive.CommentsRepoReact;
import app.data.repository.reactive.LikesReactRepo;
import app.data.repository.reactive.PostRepoReact;
import app.data.repository.reactive.ProfileRepoReact;
import app.data.repository.reactive.RecentPostReactRepo;
import app.data.repository.reactive.TrendingPostRepoReact;
import app.generic.model.ShortProfile;
import app.http.model.requests.GetSinglePostRequest;
import app.http.model.requests.LikePostRequest;
import app.http.model.requests.PostCommentForPostRequest;
import app.http.model.responses.LikePostResponse;
import app.http.model.responses.ListOfCommentersForPostResponse;
import app.http.model.responses.ListOfProfilesLikedPostResponse;
import app.http.model.responses.PostAddedResponse;
import app.http.model.responses.PostCommentForPostResponse;
import app.utility.DateTimeUtility;
import app.utility.IdGeneratorUtility;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/secure/post")
@Slf4j
public class PostController {
    
    @Autowired private PostRepoReact postrepo;
    @Autowired private CommentsRepoReact commentsrepo;
    @Autowired private LikesReactRepo likesrepo;
    @Autowired private IdGeneratorUtility idc;
    @Autowired private RecentPostReactRepo recentpostrepo;
    @Autowired private TrendingPostRepoReact trendingpostrepo;
    @Autowired private ModelMapper mapper;
    @Autowired private DateTimeUtility datetime;   
    @Autowired private ProfileRepoReact profilerepo;
    
    
    /*
     * addPost(userId, post) - add into recentposts and posts till 5 days remove from recentposts after 5 days 
     *                       //dates to be implemented and are very imp 
     *                       // updating profile with update date is imp 
     *                       //create instance of Likes entity
     *                       //create instance of Comments entity 
     * 
     * getHomePost(requester<userId>) - query recentposts
     *                       //Logic -
     *                          check how many users the userId is following from followingList
     *                          get first 50 posts from those users 
     *                          sort the posts with Stream sort with param LocalDatetime 
     *                          event stream the same with clilent end 
     * getTrendingPosts(string requester<userId>) // just retrieve the data from trendingPost and give it away limit it for 50 posts
     * 
     * getPost(String requester<userId>, string postId) getexact post marked with postId
     * 
     * scheduleTrendingPosts()
     *                       //Logic 
     *                          run aggregate function to fulfil this requerememnt 
     * 
     * likePost(string requester<userId>, string postId)
     *          //Logic
     *              increament the likecount of the post entity
     *              get userId and get the instance of Likes table 
     * getLikerList(string requester<userId>, string postId)
     *          //Logic
     *              get List of LikedBy by postId
     *              extract profile from userId
     *              feed up shortprofile and give back  
     * addcomments(string requester<userId>, Comment)
     *          //Logic
     *          increeament the 
     */
    
    //@SuppressWarnings("static-access")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("addOrUpdatePost")
	public PostAddedResponse addPost(@RequestBody Post post1) {
    	Profile profile = new Profile();
    	if(post1.getPostId()==null || post1.getPostId()=="") {
    		profile = profilerepo.getByUserId(post1.getUser_Id()).block();
	    	try {
	    		
	    		String postId = idc.getIdForDocument(DataBaseConstants.PostDocName);
	    		
	    		Likes likesinstance = Likes.builder().postId(postId).build();
	    		String likesId = likesrepo.save(likesinstance).block().getId();
	    		
	    		post1.setCreatedOn(datetime.getNow());
	    		post1.setUpdatedOn(datetime.getNow());
	    		post1.setUpdatedBy(post1.getUser_Id());
	    		post1.setCreatedBy(post1.getUser_Id());
	    		post1.setPostId(postId);
	    		post1.setLikes_id(likesId);
	    		post1.setRecent(true);
	    		post1.setUsername(profile.getUsername());
	    		post1.setProfilepicurl(profile.getProfilePicUrl());
	    		postrepo.save(post1).block();
	    		recentpostrepo.save(mapper.map(post1, RecentPost.class)).block();
	    		return PostAddedResponse.builder().status(true).userId(post1.getUser_Id()).postId(postId).build();
	    	}catch(Exception e) {
	    		System.out.println("reach exception");
	    		idc.detroyId(DataBaseConstants.PostDocName);
	    		return PostAddedResponse.builder().status(false).errorCode(ErrorConstants.InternalError).errorMessage(e.getLocalizedMessage()).build();
	    	}
    	}else {
    		if(post1.isRecent()==true) {
    			Post p1 = postrepo.findByPostId(post1.getPostId()).block();
    			if(p1!=null){
    				String id = p1.getId();
    				mapper.map(post1, p1);
    				p1.setUpdatedBy(post1.getUser_Id());
    				p1.setUpdatedOn(datetime.getNow());
    				p1.setId(id);
	    			postrepo.save(p1).block();
	    			recentpostrepo.save(mapper.map(p1, RecentPost.class)).block();
	    			return PostAddedResponse.builder().postId(post1.getPostId()).userId(post1.getUser_Id()).status(true).build();
    			}else {
    				return PostAddedResponse.builder().status(false).errorCode(ErrorConstants.InternalError).errorMessage("Profile Does not exist").build();
				}
    		}else {
    			Post p1 = postrepo.findByPostId(post1.getPostId()).block();
    			if(p1!=null){
    				String id = p1.getId();
    				mapper.map(post1, p1);
    				p1.setUpdatedBy(post1.getUser_Id());
    				p1.setUpdatedOn(datetime.getNow());
    				p1.setId(id);	    			
	    			postrepo.save(p1).block();  
	    			return PostAddedResponse.builder().postId(post1.getPostId()).userId(post1.getUser_Id()).status(true).build();
    		}
    			return PostAddedResponse.builder().status(false).errorCode(ErrorConstants.InputNotValid).errorMessage("Input is Null").build();
		}
    	
    }
    }  
    
    @GetMapping("getAllPosts") public Flux<Post> getAllPosts(){
    	return postrepo.findAll();
    }
    
    @PostMapping("getSinglePost") public Mono<Post> getSinglePost(@RequestBody GetSinglePostRequest req) {
    	if(req.getPostId()!=null || req.getPostId()!="" && req.getRequester()!=null || req.getRequester()!="") {
    		try {
		    	Post p1 = postrepo.findByPostId(req.getPostId()).block();
		//    	List<String> l1 = likesrepo.findById(p1.getLikes_id()).block().getLikedBy();
		    	if(likesrepo.findById(p1.getLikes_id()).block().getLikedBy()!=null) {
			    	if(likesrepo.findById(p1.getLikes_id()).block().getLikedBy()
			    			.parallelStream().anyMatch(userId -> userId==req.getRequester())) {
			    		p1.setLikedByRequester(true);
			    	}else {
						p1.setLikedByRequester(false);
					}
		    	}else {
		    		p1.setLikedByRequester(false);
		    	}
		    	return Mono.just(p1);
    		}catch(Exception e){
        		return Mono.just(Post.builder().postId(e.getMessage()).build());
    		}
    	}else {
    		System.out.println("request null");
    		return Mono.just(Post.builder().postId(null).build());
    	}
    }
    
    @PostMapping("likePost") public LikePostResponse likePost(@RequestBody LikePostRequest req) {
    	
    	if((req.getPostId()!=null && req.getUserId()!=null) || (req.getPostId()!="" && req.getUserId()!="")) {
    		
    		try {
    			Likes likes = likesrepo.getLikesByPostId(req.getPostId()).block();
    			Post post = postrepo.findByPostId(req.getPostId()).block();
    			likes.setLikedBy(Arrays.asList(req.getUserId()));
    			likes.setLikeCount(String.valueOf(Integer.parseInt(likes.getLikeCount()+1)));
    			post.setLikesCount(String.valueOf(Integer.parseInt(post.getLikesCount()+1)));
    			String likescount = likesrepo.save(likes).block().getLikeCount();
    			postrepo.save(post).block();
    			return LikePostResponse.builder().status(true).likecount(likescount).requester(req.getUserId()).build();
    		}catch (Exception e) {
    			return LikePostResponse.builder().status(false).errorCode(ErrorConstants.InternalError)
    					.errorMessage(e.getMessage()).build();
			}
    	}else {
			return LikePostResponse.builder().status(false).errorCode(ErrorConstants.InputNotValid).errorMessage("Inupt is Null").build();
		}
    }
    
    @GetMapping("getListofLikedProfilesforPost/{postId}") public ListOfProfilesLikedPostResponse getListofLikedProfilesforPost(@PathVariable String postId) {
    	List<ShortProfile> profiles = new ArrayList<ShortProfile>(); 
    	ShortProfile profile = new ShortProfile();
    	if(postId!=null && postId!="") {
    		try {
	    		Likes likes = likesrepo.getLikesByPostId(postId).block();
	    		likes.getLikedBy().parallelStream().forEach(data -> {
	    			Profile p1 = profilerepo.getByUserId(data).block();
	    			profile.setUserId(p1.getUserId());
	    			profile.setProfilepicurl(p1.getProfilePicUrl());
	    			profile.setUsername(p1.getUsername());
	    			profiles.add(profile);
	    		});
	    		return ListOfProfilesLikedPostResponse.builder().status(true).profiles(profiles).build();
    		 }catch (Exception e) {
    	    	
    			 return ListOfProfilesLikedPostResponse.builder().status(false).errorCode(ErrorConstants.InternalError).errorCode(e.getMessage()).build();
			}
    	}else {
    		return ListOfProfilesLikedPostResponse.builder().status(false).errorCode(ErrorConstants.InputNotValid).errorCode("Input Null").build();
    	}
    }
    
    @PostMapping("postCommentForPost") public PostCommentForPostResponse postCommentForPost(PostCommentForPostRequest req) {
    	if(req.getPostId()!=null || req.getPostId()!="" && req.getRequester()!=null || req.getRequester()!="") {
    		try {
		    		Post post = postrepo.findByPostId(req.getPostId()).block();
		    		Profile p = profilerepo.getByUserId(req.getRequester()).block();
		    		Comments comm = commentsrepo.save(Comments.builder().comment(req.getComment()).commentedBy(req.getRequester())
		    				.commentedByProfilePicUrl(p.getProfilePicUrl()).commentedOn(datetime.getNow())
		    				.commentedByusername(p.getUsername()).postID(req.getPostId()).build()).block();
		    		post.setComments_ids(Arrays.asList(comm.getId()));
		    		post.setCommentsCount(String.valueOf(Integer.parseInt(post.getCommentsCount()+1)));
		    		postrepo.save(post).block();
		    		
		    		return PostCommentForPostResponse.builder().status(true).comments(comm).build();
    		}catch (Exception e) {
    			return PostCommentForPostResponse.builder().status(false).errorCode(ErrorConstants.InternalError)
        				.errorMessage(e.getMessage()).build();
			}
    	}else {
    		return PostCommentForPostResponse.builder().status(false).errorCode(ErrorConstants.InputNotValid)
    				.errorMessage("Input Null").build();
    	}
    }

    @GetMapping("getListOfCommentersForPost") public ListOfCommentersForPostResponse getListOfCommentersForPost() {
    	return null;
    }
    
    
}