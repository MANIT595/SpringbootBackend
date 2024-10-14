package com.manikanta.microservices.project.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "SpringBoot User Microservice",
							description = "Hello World",
							version = "1.0v"
))
@EnableFeignClients
@EnableCaching
public class UserServiceApplication {

//	@Bean
//	public RestTemplate restTemplate(){
//		return new RestTemplate();
//	}

//	@Bean
//	public WebClient webClient(){
//		return WebClient.builder().build();
//	}
//@Bean
//public WebClient webClient(){
//	return WebClient.builder().build();
//}

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
