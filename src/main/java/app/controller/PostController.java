package app.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.data.repository.reactive.CommentsRepoReact;
import app.data.repository.reactive.LikesReactRepo;
import app.data.repository.reactive.PostRepoReact;

@RestController
@RequestMapping("/secure/admin")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Postcontroller {
    
    @Autowired private PostRepoReact postrepo;
    @Autowired private CommentsRepoReact commentsrepo;
    @Autowired private LikesReactRepo likesrepo;
    
    
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
    
    
    
}
