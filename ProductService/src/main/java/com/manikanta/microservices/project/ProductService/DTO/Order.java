package com.manikanta.microservices.project.ProductService.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Order implements Serializable{
    private int orderId;
    private long userId;
    private String orderStatus;
    private List<Long> productsList;
}
