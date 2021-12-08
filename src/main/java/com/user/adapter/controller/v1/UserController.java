package com.user.adapter.controller.v1;

import static com.user.APPConstant.V1;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.adapter.dto.AuthRequest;
import com.user.adapter.dto.AuthResponse;
import com.user.adapter.dto.Response;
import com.user.adapter.entities.UserDetails;
import com.user.domainlayer.service.UserService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping(V1)
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	private static final Logger log = (Logger) LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userSvc;

	
	@PostMapping(value = "/login")
	public Mono<ResponseEntity<Response<AuthResponse>>> login(@RequestBody AuthRequest authReq) {

		return userSvc.login(authReq).map(user -> {
			return new ResponseEntity<Response<AuthResponse>>(
					new Response<AuthResponse>(true, "User Authenticated Successfully", user), HttpStatus.OK);
		}).defaultIfEmpty(new ResponseEntity<Response<AuthResponse>>(
				new Response<AuthResponse>(false, "Invalid User Details"), HttpStatus.UNAUTHORIZED));

	}

	@PostMapping(value = "/register")
	public Mono<ResponseEntity<Response<UserDetails>>> save(@RequestBody UserDetails _user) {

		return userSvc.save(_user).map(user -> {
			return new ResponseEntity<Response<UserDetails>>(
					new Response<UserDetails>(true, "Record Saved Successully", user), HttpStatus.OK);
		}).defaultIfEmpty(new ResponseEntity<Response<UserDetails>>(
				new Response<UserDetails>(false, "Record Not Saved Successully"), HttpStatus.NOT_FOUND));

	}

	@PutMapping(value = "/update/{id}")
	public Mono<ResponseEntity<Response<UserDetails>>> update(@PathVariable("id") String id,
			@RequestBody UserDetails _user) {

		return userSvc.update(id, _user).map(user -> {
			return new ResponseEntity<Response<UserDetails>>(
					new Response<UserDetails>(true, "Record Saved Successully", user), HttpStatus.OK);
		}).defaultIfEmpty(new ResponseEntity<Response<UserDetails>>(
				new Response<UserDetails>(false, "Record Not Saved Successully"), HttpStatus.NOT_FOUND));

	}

	@GetMapping(value = "/")
	public Mono<ResponseEntity<Response<List<UserDetails>>>> allUsers() {

		return userSvc.allUsers().collectList()
				.map(list -> new ResponseEntity<Response<List<UserDetails>>>(
						new Response<List<UserDetails>>(true, "Record retrieved successully", list), HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<Response<List<UserDetails>>>(
						new Response<List<UserDetails>>(false, "Record not found"), HttpStatus.NOT_FOUND));

	}

	@GetMapping(value = "/{id}")
	public Mono<ResponseEntity<Response<UserDetails>>> userById(@PathVariable("id") String id) {

		return userSvc.findById(id)
				.map(user -> new ResponseEntity<Response<UserDetails>>(
						new Response<UserDetails>(true, "Record retrieved successully", user), HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<Response<UserDetails>>(
						new Response<UserDetails>(false, "Record not found"), HttpStatus.NOT_FOUND));

	}
	
	@GetMapping(value = "/login/userinfo")
	public Mono<ResponseEntity<Response<UserDetails>>> loginUserInfo() {

		
		return userSvc.loginUserInfo()
				.map(user -> new ResponseEntity<Response<UserDetails>>(
						new Response<UserDetails>(true, "Record retrieved successully", user), HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<Response<UserDetails>>(
						new Response<UserDetails>(false, "Record not found"), HttpStatus.NOT_FOUND));

	}

}
