package com.manikanta.microservices.project.UserService.Service;

import com.manikanta.microservices.project.UserService.DTO.UserDtoOrders;
import com.manikanta.microservices.project.UserService.DTO.UserResponse;
import com.manikanta.microservices.project.UserService.DTO.UserDTO;
import com.manikanta.microservices.project.UserService.Entity.User;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {
//    UserDTO saveEmployee(UserDTO employeeDto);
//
//    UserDTO getEmployeeById(Long employeeId);

    ResponseEntity<UserDTO> getUser(Long userId) throws Exception;

    ResponseEntity<String> deleteUser(Long userId);

    ResponseEntity<String> addUser(User user);

    ResponseEntity<String> deleteUserByEmail(String email);

    UserResponse getUsers(int pageNo, int pageSize, String pageSortBy, String sortDir);

    UserDtoOrders getUsersOrders(Long userId);

    ResponseEntity<UserDTO> updateUserById(Long userId, User user);

    boolean isFeatureEnabled(Long userId, Long weblabId);

    UserDTO getUserDefault();

    String verify(User user);
}
