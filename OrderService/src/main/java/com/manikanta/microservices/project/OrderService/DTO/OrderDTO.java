package com.manikanta.microservices.project.OrderService.DTO;

import jakarta.persistence.*;
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
        private long orderId;
        private long userId;
        private String orderStatus;
        private List<ProductDTO> productsList;
}
