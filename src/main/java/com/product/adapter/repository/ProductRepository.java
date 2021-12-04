package com.product.adapter.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.product.adapter.entities.ProductDetails;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<ProductDetails, String> {

	public Mono<ProductDetails> findByIdAndActive(String id, boolean active);
	
	public Flux<ProductDetails> findByActive(boolean active);

}
