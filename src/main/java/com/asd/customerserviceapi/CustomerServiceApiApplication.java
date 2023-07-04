package com.asd.customerserviceapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class CustomerServiceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApiApplication.class, args);
	}


	@Value("${address.service.url}")
	private String addressServiceUrl;

	@Bean
	public WebClient webClient(){
		return WebClient.builder().build();
	}

}
