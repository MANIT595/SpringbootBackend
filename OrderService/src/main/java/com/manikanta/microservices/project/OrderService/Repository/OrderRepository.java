package com.manikanta.microservices.project.OrderService.Repository;

import com.manikanta.microservices.project.OrderService.DTO.OrderDTO;
import com.manikanta.microservices.project.OrderService.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findByUserId(Long userId);
}
