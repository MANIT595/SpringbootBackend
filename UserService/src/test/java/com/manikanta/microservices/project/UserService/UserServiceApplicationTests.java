package com.manikanta.microservices.project.UserService;

import com.manikanta.microservices.project.UserService.DTO.UserDTO;
import com.manikanta.microservices.project.UserService.Entity.User;
import com.manikanta.microservices.project.UserService.Service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private UserService userService;


	@Test
	@Transactional
	public void testGetUser_Success() throws Exception {
		User user = new User();
		user.setFirstName("test");
		user.setLastName("test");
		user.setEmail("testing1@gmail.com");
		user.setPassword("test");

		userService.addUser(user);

		ResponseEntity<UserDTO> response = userService.getUser(11L);
		Assert.assertNotNull(response);
		Assert.assertEquals(response.getBody().getEmail(), "testing1@gmail.com");
		Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
		System.out.println(user.getUserId());
	}

	@Test
	@Transactional
	public void testGetUser() throws Exception {

		ResponseEntity<UserDTO> response = userService.getUser(10L);
		System.out.println(response.getBody());
		Assert.assertNotNull(response);
		Assert.assertEquals(response.getBody().getEmail(), "laura.hall@example.com");
		Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

}
