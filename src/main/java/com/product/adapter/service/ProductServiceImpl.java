package com.product.adapter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.adapter.entities.ProductDetails;
import com.product.adapter.repository.ProductRepository;
import com.product.domainlayer.service.ProductService;
import com.product.server.exceptions.DuplicateRecordException;
import com.product.server.exceptions.InvalidInputException;
import com.product.server.secutiry.JWTUtil;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger log = (Logger) LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductRepository prodRepo;
  

	@Override
	public Mono<ProductDetails> findById(String id) {
		log.info("Find Product By Id=" + id);

		if (id == null) {
			Mono<ProductDetails> fallback = Mono.error(new InvalidInputException("Invalid Id: " + id));
			return fallback;
		}
		return prodRepo.findByIdAndActive(id, true);
	}

	@Override
	public Mono<ProductDetails> save(ProductDetails _prod) {

		log.info("Save Product Details=" + _prod);

		if (_prod == null || !_prod.isValid()) {
			Mono<ProductDetails> fallback = Mono.error(new InvalidInputException(ProductDetails.invalidMsg()));
			return fallback;
		}

		return prodRepo.save(_prod);
	}

	@Override
	public Mono<ProductDetails> update(String id, ProductDetails _prod) {

		log.info("Update Product Id=" + _prod.getId() + " Details=" + _prod);

		if (_prod == null || !_prod.isValid()) {
			Mono<ProductDetails> fallback = Mono.error(new InvalidInputException(ProductDetails.invalidMsg()));
			return fallback;
		}

		return prodRepo.findByIdAndActive(id, true).flatMap(prod -> {
			prod.setDescription(_prod.getDescription());
			prod.setImage(_prod.getImage());
			prod.setPrice(_prod.getPrice());
			prod.setName(_prod.getName());
			return prodRepo.save(prod);
		}).switchIfEmpty(Mono.error(new DuplicateRecordException("Record Not Found=" + _prod.getId())));

	}

	@Override
	public Flux<ProductDetails> allProducts() {
		log.info("Find all active products");
		return prodRepo.findByActive(true);
	}
//
//	private void publishEvent(UserCreatedEvent event) {
//		log.info("Publishing on topic={} data={}",KAFKA_TOPIC_USER_CREATED_EVENT,event);
//		ListenableFuture<SendResult<String, UserCreatedEvent>> listenableFuture = kafkaTemplate.send(KAFKA_TOPIC_USER_CREATED_EVENT, event.getId(), event);
//		
//		listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, UserCreatedEvent>>() {
//
//			@Override
//			public void onSuccess(SendResult<String, UserCreatedEvent> result) {
//				log.info("Ack Received, Message published successfully on topic={}, key={}",KAFKA_TOPIC_USER_CREATED_EVENT, result.getProducerRecord().key());
//
//			}
//
//			@Override
//			public void onFailure(Throwable ex) {
//				log.error("Message cannot be published Exception={}, Event={}, Topic={}", ex.getMessage(), event, KAFKA_TOPIC_USER_CREATED_EVENT);
//				log.error("Exception=",ex);
//			}
//		});;
//	}
}
