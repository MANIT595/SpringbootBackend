package com.manikanta.microservices.project.OrderService.Service;

import com.manikanta.microservices.project.OrderService.DTO.OrderDTO;
import com.manikanta.microservices.project.OrderService.Entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    List<OrderDTO> getOrders();

    OrderDTO getOrder(Long orderId);

    void deleteOrder(Long orderId);

    void addOrder(Order user);

    List<OrderDTO> getOrderByUserId(Long userId);

    OrderDTO getUsersOrders(String orderId);

    String updateOrder(Order order);
}
