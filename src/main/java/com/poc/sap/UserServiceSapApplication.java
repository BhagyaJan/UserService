package com.poc.sap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class UserServiceSapApplication {

	public static void main(String[] args) {
		int a;
		int A;
		SpringApplication.run(UserServiceSapApplication.class, args);
		System.out.println("-- UserServiceSapApplication Started--");
	}

	@Bean
	public WebClient webClient(WebClient.Builder builder) {
		return builder.build();
	}

}
