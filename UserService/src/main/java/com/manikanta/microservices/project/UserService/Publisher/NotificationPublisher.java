package com.manikanta.microservices.project.OrderService.Publisher;

import com.manikanta.microservices.project.OrderService.Entity.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final String exchange = "order_exchange";

    public OrderPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishOrderCreatedEvent(Order order) {
        Order orderDTO = new Order(order.getOrderId(), order.getUserId(), order.getOrderStatus(), order.getProductsList());
        rabbitTemplate.convertAndSend(exchange, "order_created", orderDTO);
        System.out.println("Published order.created event for Order ID: " + order.getProductsList());
    }

    public void publishOrderCancelledEvent(Long orderId) {
        rabbitTemplate.convertAndSend(exchange, "order_cancelled", orderId);
        System.out.println("Published order.cancelled event for Order ID: " +orderId);
    }
}

