package com.manikanta.microservices.project.UserService.DTO;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoOrders {
    private UserDTO userDTO;
    List<OrderDTO> orderDTOS;
}
