package com.manikanta.microservices.project.ProductService.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    private String productDesc;
    @Column(nullable = false, unique = true)
    private String brand;
    @Column(nullable = false)
    private Long orderId;
    @Column(nullable = false)
    private Long quantity;
}
