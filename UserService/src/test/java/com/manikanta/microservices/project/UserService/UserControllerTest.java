package com.manikanta.microservices.project.UserService;

import com.manikanta.microservices.project.UserService.Controller.UserController;
import com.manikanta.microservices.project.UserService.DTO.UserDTO;
import com.manikanta.microservices.project.UserService.DTO.UserDtoOrders;
import com.manikanta.microservices.project.UserService.DTO.UserResponse;
import com.manikanta.microservices.project.UserService.Entity.User;
import com.manikanta.microservices.project.UserService.Exception.EmailAlreadyFoundException;
import com.manikanta.microservices.project.UserService.Exception.GlobalExceptionHandler;
import com.manikanta.microservices.project.UserService.Exception.UserNotFoundException;
import com.manikanta.microservices.project.UserService.Mapper.AutoUserMapper;
import com.manikanta.microservices.project.UserService.Repository.UserRepository;
import com.manikanta.microservices.project.UserService.Service.Implementation.UserServiceImplementation;
import com.manikanta.microservices.project.UserService.Service.UserService;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    public void testGetUser_Success(){
//        Long userId = 1L;
//        User user = new User();
//        user.setUserId(userId);
//        user.setEmail("test@example.com");
//
//        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
//
//        ResponseEntity<UserDTO> response = userService.getUser(userId);
//        Assert.assertNotNull(response);
//        Assert.assertEquals(response.getBody().getEmail(), "test@example.com");
//        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
//
//        verify(userRepository, times(1)).findById(userId);
//    }

    @Test
    public void testGetUser_Success() throws Exception {
        Long userId = 1L;
        UserDTO user = new UserDTO();
        user.setUserId(userId);
        user.setEmail("test@example.com");

        when(userService.getUser(userId)).thenReturn(new ResponseEntity<>(user, HttpStatus.OK));

        ResponseEntity<UserDTO> response = userService.getUser(userId);
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getBody().getEmail(), "test@example.com");
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);

        verify(userService, times(1)).getUser(userId);
    }

//    @Test(expectedExceptions = UserNotFoundException.class)
//    public void testGetUser_UserNotFound() throws Exception {
//        Long userId = 1L;
//        when(userRepository.findById(userId)).thenReturn(Optional.empty());
//        userService.getUser(userId);
//    }
}

