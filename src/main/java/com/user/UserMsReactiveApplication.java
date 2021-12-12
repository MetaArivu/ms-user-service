package com.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;

import brave.sampler.Sampler;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(title = "APIs", version = "1.0", description = "User Service Documentation APIs v1.0"))
@SpringBootApplication(exclude = { MongoAutoConfiguration.class, MongoDataAutoConfiguration.class })

@EnableReactiveMongoAuditing
public class UserMsReactiveApplication {

	public static void main(String[] args) {
		//System.setProperty("spring.webflux.base-path", APPConstant.CONTEXT_PATH);
		SpringApplication.run(UserMsReactiveApplication.class, args);
	}

	@EventListener({ EnvironmentChangeEvent.class })
	public void onRefresh() {
		System.out.println("Environment Changed..");
	}

	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
//	
//	@Bean
//    public MongoClient mongo() {
//        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/userdb");
//        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
//          .applyConnectionString(connectionString)
//          .build();
//        
//        return MongoClients.create(mongoClientSettings);
//    }
//
//    @Bean
//    public ReactiveMongoTemplate mongoTemplate() throws Exception {
//        return new ReactiveMongoTemplate(this.mongo(), "userdb");
//    }

}
