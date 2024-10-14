package com.manikanta.microservices.project.ProductService.Listener;

import com.manikanta.microservices.project.ProductService.DTO.Order;
import com.manikanta.microservices.project.ProductService.Entity.Product;
import com.manikanta.microservices.project.ProductService.Repository.ProductRepository;
import com.manikanta.microservices.project.ProductService.Service.ProductService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderEventListener {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @RabbitListener(queues = "order_created_queue")
    public void handleOrderEvents(Order order) {
        System.out.println("In handleOrderEvents");
            order.getProductsList().forEach(
                    product -> productService.updateQuantity(product,"Success"));
            System.out.println("Inventory updated for order.created");
    }

    @RabbitListener(queues = "order_cancelled_queue")
    public void handleOrderCancelledEvents(Long orderId) {
        System.out.println("In handleOrderCancelledEvents");
        List<Product> products = productRepository.findProductsByOrderId(Math.toIntExact(orderId));
        products.forEach(
                product -> productService.updateQuantity(product.getProductId(),"Cancelled"));
        System.out.println("Inventory updated for order.cancelled");

    }
}

