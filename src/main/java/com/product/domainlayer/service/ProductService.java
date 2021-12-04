package com.product.domainlayer.service;

import com.product.adapter.entities.ProductDetails;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

	public Flux<ProductDetails> allProducts();
	
	public Mono<ProductDetails> findById(String id);
	
	public Mono<ProductDetails> save(ProductDetails _prod);
	
	public Mono<ProductDetails> update(String id, ProductDetails _prod);
	
}
