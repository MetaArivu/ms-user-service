package com.user.server;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import com.user.adapter.entities.UserDetails;
import com.user.domainlayer.service.UserService;

import ch.qos.logback.classic.Logger;

@Configuration
public class AppEventListener {

	private static final Logger log = (Logger) LoggerFactory.getLogger(AppEventListener.class);
	  
	@Autowired
	private UserService userSvc;
	
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		log.info("User Service Getting Started..");
		log.info("Fetching User Details...");
		List<UserDetails> users =  userSvc.allUsers().collectList().block();
		log.info("Users={}",users.size());
	}

}