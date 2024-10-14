package com.manikanta.microservices.project.OrderService.Entity;

import com.manikanta.microservices.project.OrderService.DTO.ProductDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    @Table(name = "Orders")
    public class Order implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int orderId;
        private long userId;
        private String orderStatus;
        @ElementCollection
        private List<Long> productsList;
    }
