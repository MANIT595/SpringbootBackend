package com.manikanta.microservices.project.UserService.Tests;

import com.manikanta.microservices.project.UserService.Controller.UserController;
import com.manikanta.microservices.project.UserService.DTO.UserDTO;
import com.manikanta.microservices.project.UserService.Entity.User;
import com.manikanta.microservices.project.UserService.Repository.UserRepository;
import com.manikanta.microservices.project.UserService.Service.Implementation.UserServiceImplementation;
import com.manikanta.microservices.project.UserService.Service.UserService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;


@SpringBootTest
public class UserIntegrationTest {

    @Autowired
    private UserService userService;


    @Test
    public void testGetUser_Success() throws Exception {
        Long userId = 1L;
        User user = new User();
        user.setUserId(userId);
        user.setFirstName("test");
        user.setLastName("test");
        user.setEmail("test@gmail.com");
        user.setPassword("test");

        userService.addUser(user);

        ResponseEntity<UserDTO> response = userService.getUser(userId);
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getBody().getEmail(), "test@example.com");
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
}