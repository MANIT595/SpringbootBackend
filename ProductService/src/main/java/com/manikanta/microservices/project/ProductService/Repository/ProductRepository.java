package com.manikanta.microservices.project.ProductService.Repository;

import com.manikanta.microservices.project.ProductService.Entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.orderId IN :orderIds")
    List<Product> findProductsByOrderIds(List<Long> orderIds);

    @Query("SELECT p FROM Product p WHERE p.orderId = :orderId")
    List<Product> findProductsByOrderId(int orderId);

    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.quantity = :quantity WHERE p.productId = :productId")
    int updateQuantityByProductId(Long productId, Long quantity);
}
