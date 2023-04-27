package com.poc.sap.services;

import java.io.IOException;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.poc.sap.model.Users;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MessageConsumer {

	
	@Autowired
	private WebClient webClient;
	
	//@KafkaListener(topics = "adduser", groupId = "group_id")
	public void consume(String message) throws IOException {	
		System.out.println(" Consumed message -> " + message + " at " +LocalTime.now());
		/*
		 * Mono<String> helllo = ((WebClient) webClient).get() .uri("/v1/sap/hello")
		 * .retrieve() .bodyToMono(String.class);
		 */		
		String hello = webClient.get().uri("http://localhost:9099/v1/sap/hello").retrieve().bodyToMono(String.class).block();
		System.out.println(" hello  " +hello);
		Users createUser = (Users) webClient.post()
		        .uri("http://localhost:9099/v1/sap/addUser")
		        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) 
		        .body(BodyInserters.fromObject(message))
		        .exchange()
		        .block();
		
		//System.out.println(" createUser --   " +createUser);
		
	}
}