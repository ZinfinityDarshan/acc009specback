package app.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import app.constants.DataBaseConstants;
import app.constants.ErrorConstants;
import app.constants.ROLE;
import app.data.common.CommonDao;
import app.data.entity.Profile;
import app.data.entity.User;
import app.data.entity.UserContact;
import app.data.repository.general.UserRepository;
import app.data.repository.reactive.ProfileRepoReact;
import app.data.repository.reactive.UserContactRepoReact;
import app.exceptions.CustomException;
import app.generic.model.Role;
import app.security.JwtTokenProvider;
import app.utility.DateTimeUtility;
import app.utility.IdGeneratorUtility;


@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  private AuthenticationManager authenticationManager;
  
  @Autowired
  private CommonDao commondao;
  
  @Autowired private IdGeneratorUtility idgenerator;
  @Autowired private ProfileRepoReact profilerepo;
  @Autowired private UserContactRepoReact usercontactrepo;
  @Autowired private DateTimeUtility datetimeutil;

  public String signin(String username, String password) {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
      return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
    } catch (AuthenticationException e) {
      throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  public String signup(User user) {
	  try {
		    if (!commondao.userExistbyUsername(user.getUsername()) || commondao.userExistbyEmail(user.getEmail())) {
		      user.setPassword(passwordEncoder.encode(user.getPassword()));
		      if(user.getRoles()==null) {
		    	  List<Role> roles = new ArrayList<Role>();
		    	  roles.add(Role.valueOf("ROLE_CLIENT"));
		    	  user.setRoles(roles);
		      }
		      String userId = idgenerator.getIdForDocument("user");
		      user.setId(userId);
		      userRepository.save(user);
		      
		      Profile p = Profile.builder().userId(userId).createdOn(datetimeutil.getNow()).
		    		  updatedDate(datetimeutil.getNow()).build();
		      UserContact c = UserContact.builder().mobile(user.getPhoneno()).email(user.getEmail())
		    		  		  .userId(userId).build();
		      profilerepo.save(p).block();
		      usercontactrepo.save(c).block();
		      return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
		    } else {
		      throw new CustomException("Username/ Email is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
		    }
	  }catch (Exception e) {
		 return ErrorConstants.InternalError; 
	}
  }

  public void delete(String username) {
    userRepository.deleteByUsername(username);
  }

  public User search(String username) {
    User user = userRepository.findByUsername(username);
    if (user == null) {
      throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
    }
    return user;
  }

  public User whoami(HttpServletRequest req) {
    return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
  }

  public String refresh(String username) {
    return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
  }

}

