package com.manikanta.microservices.project.UserService.Service;

import com.manikanta.microservices.project.UserService.DTO.UserDtoOrders;
import com.manikanta.microservices.project.UserService.DTO.UserResponse;
import com.manikanta.microservices.project.UserService.DTO.UserDTO;
import com.manikanta.microservices.project.UserService.Entity.User;

public interface UserService {
//    UserDTO saveEmployee(UserDTO employeeDto);
//
//    UserDTO getEmployeeById(Long employeeId);

    UserDTO getUser(Long userId) throws Exception;

    void deleteUser(Long userId);

    void addUser(User user);

    UserResponse getUsers(int pageNo, int pageSize, String pageSortBy, String sortDir);

    UserDtoOrders getUsersOrders(Long userId);

    UserDTO updateUserById(Long userId, User user);

//    String verify(User user);
}
