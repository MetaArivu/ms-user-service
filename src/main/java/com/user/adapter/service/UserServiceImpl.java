package com.user.adapter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.adapter.dto.AuthRequest;
import com.user.adapter.dto.AuthResponse;
import com.user.adapter.entities.UserDetails;
import com.user.adapter.repository.UserRepository;
import com.user.domainlayer.service.UserService;
import com.user.server.aop.RequestAspect;
import com.user.server.exceptions.DuplicateRecordException;
import com.user.server.exceptions.InvalidInputException;
import com.user.server.secutiry.JWTUtil;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = (Logger) LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private JWTUtil jtwUtil;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public Mono<AuthResponse> login(AuthRequest authReq) {
		log.info("Authenticating User="+authReq);
		return userRepo.findByLoginIdAndActive(authReq.getLoginId(), true)
					   .filter(user -> 
					   		{
					   			return user.getPassword().equals(authReq.getPassword());
					   		})
					   .map(user -> {
						   String token = jtwUtil.generateToken(user);
						   return new AuthResponse(token);
					   });
	}
	
	@Override
	public Mono<UserDetails> findById(String id) {
		log.info("Find User By Id="+id);

		if(id == null) {
			Mono<UserDetails> fallback = Mono.error( new InvalidInputException("Invalid Id: "+id));
			return fallback;
		}
		return userRepo.findByIdAndActive(id, true);
	}
	
	
	@Override
	public Mono<UserDetails> save(UserDetails _user) {
		
		log.info("Save User Details="+_user);

		if(_user==null || !_user.isValid()) {
			Mono<UserDetails> fallback = Mono.error( new InvalidInputException(UserDetails.invalidMsg()));
			return fallback;
		}
		
		return userRepo.findByLoginIdAndActive(_user.getLoginId(), true)
				.flatMap(prd ->{
				    Mono<UserDetails> fallback = Mono.error(new DuplicateRecordException("Duplicate LoginId " + _user.getLoginId()));
					return fallback;
				})
				.switchIfEmpty(userRepo.save(_user));
	} 
	
	@Override
	public Mono<UserDetails> update(String id, UserDetails _user) {
		
		log.info("Update User Id="+_user.getId()+" Details="+_user);

		if(_user==null || !_user.isValid()) {
			Mono<UserDetails> fallback = Mono.error( new InvalidInputException(UserDetails.invalidMsg()));
			return fallback;
		}
		
		return userRepo
				.findByIdAndActive(id, true)
				.flatMap(user ->{
					user.setAge(_user.getAge());
					user.setDob(_user.getDob());
					user.setEmail(_user.getEmail());
					user.setFirstName(_user.getFirstName());
					user.setLastName(_user.getLastName());
					user.setLoginId(_user.getLoginId());
					user.setMiddleName(_user.getMiddleName());
					user.setPassword(_user.getPassword());
					return userRepo.save(user);
				})
				.switchIfEmpty( Mono.error(new DuplicateRecordException("Record Not Found=" + _user.getId())));
				
				
	} 
	
	
	@Override
	public Flux<UserDetails> allUsers() {
		log.info("Find all active users");

		return userRepo.findByActive(true);
	}
	
}
