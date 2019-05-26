package app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.constants.ErrorConstants;
import app.data.entity.User;
import app.data.repository.general.UserRepository;
import app.http.model.requests.SignupRequest;
import app.http.model.responses.EntityAddedResponse;
import app.http.model.responses.SecureLoginResponse;
import app.http.model.responses.UserDataDTO;
import app.http.model.responses.UserResponseDTO;
import app.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/users")
@Api(tags = "users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private ModelMapper modelMapper;
  
  @Autowired UserRepository userrepo;
  

  @PostMapping("/signin")
  @ApiOperation(value = "${UserController.signin}")
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 422, message = "Invalid username/password supplied")})
  public Mono<SecureLoginResponse> login(//
      @ApiParam("Username") @RequestParam String username, //
      @ApiParam("Password") @RequestParam String password) {
    return Mono.just(SecureLoginResponse.builder().username(userrepo.findByUsername(username).getId()).
    		token(userService.signin(username, password)).status(true).build());
    		
  }

//  @PostMapping("/signup")
//  @ApiOperation(value = "${UserController.signup}")
//  @ApiResponses(value = {//
//      @ApiResponse(code = 400, message = "Something went wrong"), //
//      @ApiResponse(code = 403, message = "Access denied"), //
//      @ApiResponse(code = 422, message = "Username is already in use"), //
//      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
//  public String signup(@ApiParam("Signup User") @RequestBody UserDataDTO user) {
//    return userService.signup(modelMapper.map(user, User.class));
//  }
  
  @PostMapping("/signup")
  public SecureLoginResponse signup (@RequestBody SignupRequest req) {
	  String token = userService.signup(modelMapper.map(req, User.class));
	  SecureLoginResponse res;
	  if(token!=ErrorConstants.InternalError) {
		 res  = SecureLoginResponse.builder().username(userrepo.findByUsername(req.getUsername()).getId()).
				  status(true).token(token).build();
		 return res;
	  }
	  res = SecureLoginResponse.builder().username(req.getUsername()).status(false)
			  .token("").errorCode(ErrorConstants.InternalError).errorMessage("Internal Error").build();
	  return res;
  }

  @DeleteMapping(value = "/{username}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "${UserController.delete}")
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 404, message = "The user doesn't exist"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public String delete(@ApiParam("Username") @PathVariable String username) {
    userService.delete(username);
    return username;
  }

  @GetMapping(value = "/{username}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "${UserController.search}", response = UserResponseDTO.class)
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 404, message = "The user doesn't exist"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public UserResponseDTO search(@ApiParam("Username") @PathVariable String username) {
    return modelMapper.map(userService.search(username), UserResponseDTO.class);
  }

  @GetMapping(value = "/me")
  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
  @ApiOperation(value = "${UserController.me}", response = UserResponseDTO.class)
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public UserResponseDTO whoami(HttpServletRequest req) {
    return modelMapper.map(userService.whoami(req), UserResponseDTO.class);
  }

  @GetMapping("/refresh")
  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
  public String refresh(HttpServletRequest req) {
    return userService.refresh(req.getRemoteUser());
  }
  
  @GetMapping("/getAllUsers")
  public List<User> getAllUsers(){
	return userrepo.findAll();
  }

}

