package com.user.server.secutiry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import com.user.server.config.AppProperties;

import reactor.core.publisher.Mono;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebSecurityConfig {

	private static final Logger log = (Logger) LoggerFactory.getLogger(WebSecurityConfig.class);

	@Autowired
    private AuthenticationManager authenticationManager;
    
	@Autowired
	private SecurityContextRepository securityContextRepository;

	@Autowired
	private MyAuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
	private AppProperties appProp;
    @Bean
    public SecurityWebFilterChain securitygWebFilterChain(ServerHttpSecurity http) {
    	//log.info("Authenticating Request URL={}",http);
    	//log.info("Public URLS={}",appProp.getPublicAPI().toString());
        return http
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler((swe, e) -> 
                    Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN))
                ).and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository)
                .authorizeExchange()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .pathMatchers(appProp.getPublicAPI()).permitAll()
                .anyExchange().authenticated()
                
                .and()
                .build();
    }
}

