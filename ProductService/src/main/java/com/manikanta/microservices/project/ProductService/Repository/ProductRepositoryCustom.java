package com.manikanta.microservices.project.ProductService.Repository;

import com.manikanta.microservices.project.ProductService.Entity.Product;

import java.util.List;

public interface ProductRepositoryCustom {

    List<Product> findProductsByCriteria(String brand, String productDesc, String productName, Long quantity);
}
