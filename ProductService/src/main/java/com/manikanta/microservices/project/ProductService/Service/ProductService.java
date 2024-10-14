package com.manikanta.microservices.project.ProductService.Service;

import com.manikanta.microservices.project.ProductService.DTO.ProductDTO;
import com.manikanta.microservices.project.ProductService.Entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<Product> getProducts();

    Product getProduct(Long userId);

    void deleteProduct(Long userId);

    void addProduct(Product user);

    void updateQuantity(Long productId, String status);

    List<Product> getProductsByOrderId(List<Long> orderId);
}
