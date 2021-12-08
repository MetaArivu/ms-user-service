package com.user.domainlayer.service;

import com.user.adapter.dto.AuthRequest;
import com.user.adapter.dto.AuthResponse;
import com.user.adapter.entities.UserDetails;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

	public Flux<UserDetails> allUsers();

	public Mono<UserDetails> findById(String id);
	
	public Mono<UserDetails> save(UserDetails _user);
	
	public Mono<UserDetails> update(String id, UserDetails _user);
	
	public Mono<AuthResponse> login(AuthRequest authReq);
	
	public Mono<UserDetails> loginUserInfo();

	
}
