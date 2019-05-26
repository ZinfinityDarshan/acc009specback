package app.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
import app.data.entity.IDConfiguration;
import app.data.entity.Profile;
import app.data.entity.Subject;
import app.data.entity.SubjectConstant;
import app.data.repository.general.UserRepository;
import app.data.repository.reactive.FollowersRepoReact;
import app.data.repository.reactive.FollowingRepoReact;
import app.data.repository.reactive.IDConfigurationRepoReact;
import app.data.repository.reactive.ProfileRepoReact;
import app.data.repository.reactive.SubjectConstantRepoReact;
import app.data.repository.reactive.SubjectReactRepo;
import app.http.model.requests.AddSubjectsToProfileRequest;
import app.http.model.requests.FollowUserRequest;
import app.http.model.responses.AddSubjectToProfileResponse;
import app.http.model.responses.FollowUserResponse;
import app.service.ProfileService;
import app.utility.DateTimeUtility;
import app.utility.IdGeneratorUtility;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/secure/profile")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProfileController {

	@Autowired private ProfileService profileservice;
	@Autowired private ProfileRepoReact profilerepo;
	@Autowired private IDConfigurationRepoReact idcrepo;
	@Autowired private SubjectConstantRepoReact subconrepo;
	@Autowired private UserRepository userrepo;
	@Autowired private SubjectReactRepo subrepo;
	@Autowired private DateTimeUtility timeutil;
	@Autowired private FollowersRepoReact followerrepo;
	@Autowired private FollowingRepoReact followingrepo;

	
	@GetMapping("hi")
	public String hi() {
		log.info("hi user");
		return "hello";
	}
	
	@GetMapping("{id}")
	public Flux<Profile>getMyProfile(@PathVariable String id) {
		return profilerepo.findAll();
	}
	
	@PostMapping("addprofile")
	public Mono<Profile> addProfile() {
		List<String> profesion = new ArrayList<String>();
		profesion.add("writer");
		return profilerepo.save(Profile.builder().f_name("darshan").l_name("redkar").bio("writer").professsion(profesion).id("prl_0001").build());
	}
	
//	@PostMapping("addIdGenerator") public Mono<IDConfiguration> createIdGenerator() {
//		IDConfiguration id1 = new IDConfiguration("1", "pst", "0", DataBaseConstants.PostDocName);
//		IDConfiguration id2 = new IDConfiguration("2", "usr", "0", DataBaseConstants.UserDocName);
//		idcrepo.save(id2);
//		return idcrepo.save(id1);
//	} 
	
	@GetMapping("getAllIds")
	public Flux<IDConfiguration> getAll(){
		return idcrepo.findAll();
	}
	
	@PostMapping("addSubjectsToProfile") public AddSubjectToProfileResponse addSubjectsToProfile(AddSubjectsToProfileRequest req){
		AddSubjectToProfileResponse res = new AddSubjectToProfileResponse();
		if(userrepo.findById(req.getUserId()) != null ) {
			try {
				
				Profile p = profilerepo.getByUserId(req.getUserId()).block();
				Subject s =  Subject.builder().userId(req.getUserId()).subjects(req.getSubjects()).build();
				String subId = subrepo.save(s).block().getId();
				p.setSubjectId(subId);
				profilerepo.save(p).block();
				res.setStatus(true);
				res.setSubjects(s.getSubjects());
				res.setUserId(req.getUserId());
			}catch (Exception e) {
				res.setUserId(req.getUserId());
				res.setStatus(false);
				res.setErrorCode(ErrorConstants.InternalError);
				res.setErrorMessage(e.getMessage());
				log.info("addSubjectsToProfile"+e.getLocalizedMessage());
			}
		}else {
			res.setUserId(req.getUserId());
			res.setStatus(false);
			res.setErrorCode(ErrorConstants.InputNotValid);
			res.setErrorMessage("userId is not valid / no entity is available for this profile");
		}
		return res;
	}
	
	/*
	 * @GetMapping("addSubjects") public Flux<SubjectConstant> addSubjects(){
	 * List<String> subjects = new ArrayList<String>(); subjects.add("dance");
	 * subjects.add("music"); subjects.add("art"); subjects.add("theater");
	 * subjects.add("design"); subjects.add("stageArt"); subjects.add("monoAct");
	 * subjects.add("poetry"); subjects.add("writer"); subjects.add("singing"); for
	 * (String string : subjects) {
	 * subconrepo.save(SubjectConstant.builder().subjectname(string).build()).block(
	 * ); } return subconrepo.findAll(); }
	 */
	
	@GetMapping("getTime") public LocalDateTime getDateTime() {
		return timeutil.getNow();
	}
	
	@PostMapping("followUser") public FollowUserResponse followUser(FollowUserRequest req) {
		FollowUserResponse res = FollowUserResponse.builder().build();
		if(req != null) {
			try {
				profileservice.followUser(req.getFollower(), req.getFollowing());
			}catch (Exception e) {
				
			}
		}else {
			res.builder().errorCode(ErrorConstants.InputNotValid).errorMessage("input is not valid").status(false).build();
		}
		return res;
	}
	
}
