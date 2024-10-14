package com.manikanta.microservices.project.OrderService.Controller;

import com.manikanta.microservices.project.OrderService.DTO.OrderDTO;
import com.manikanta.microservices.project.OrderService.DTO.ProductDTO;
import com.manikanta.microservices.project.OrderService.Entity.Order;
import com.manikanta.microservices.project.OrderService.Publisher.OrderPublisher;
import com.manikanta.microservices.project.OrderService.Repository.OrderRepository;
import com.manikanta.microservices.project.OrderService.Service.OrderService;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    private MeterRegistry meterRegistry;




    @GetMapping("/cancel/{orderId}")
    public String cancelOrder(@PathVariable Long orderId) {
        return "Order cancelled and event published.";
    }

    @GetMapping()
    public List<OrderDTO> getProducts() {
        return orderService.getOrders();
    }

    @GetMapping("{order-id}")
    public OrderDTO getProductById(@PathVariable("order-id") Long id) {
        return orderService.getOrder(id);
    }

    @GetMapping("/user/{user-id}")
    public List<OrderDTO> getOrdersByUserId(@PathVariable("user-id") Long userId) {
        meterRegistry.counter("ORDERS_getOrdersByUserId").increment();
        return orderService.getOrderByUserId(userId);
    }

    @DeleteMapping("{order-id}")
    public void deleteUserById(@PathVariable("order-id") Long id) {
        orderService.deleteOrder(id);
    }

    @PostMapping
    public void addProduct(@RequestBody Order order){
        orderService.addOrder(order);
    }

    @PatchMapping
    public String updateOrder(@RequestBody Order order){
        return orderService.updateOrder(order);
    }
}
