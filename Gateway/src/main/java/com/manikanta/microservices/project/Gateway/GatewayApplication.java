package com.manikanta.microservices.project.Gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@EnableDiscoveryClient
////@EnableCaching
@CrossOrigin("http://localhost:3000")
public class GatewayApplication {

	@Bean
	public RestTemplate template(){
		return new RestTemplate();
	}

	public static void main(String[] args) {

		SpringApplication.run(GatewayApplication.class, args);


	}

}
