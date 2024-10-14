package com.manikanta.microservices.project.UserService.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private int orderId;
    private long userId;
    private String orderStatus;
    private List<ProductDTO> productsList;
}
