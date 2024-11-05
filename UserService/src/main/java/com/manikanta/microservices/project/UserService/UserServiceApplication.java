package com.manikanta.microservices.project.UserService;
import com.manikanta.microservices.project.UserService.Service.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "SpringBoot User Microservice",
							description = "Hello World",
							version = "1.0v"
))
@EnableFeignClients
//@EnableCaching
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

	@Autowired
	private static UserService userService;

	public static void main(String[] args) {
		System.out.println("USer Servie bean is"+ (userService != null ? "loaded": "Not loaded"));
		SpringApplication.run(UserServiceApplication.class, args);

		System.out.println();
	}

}
