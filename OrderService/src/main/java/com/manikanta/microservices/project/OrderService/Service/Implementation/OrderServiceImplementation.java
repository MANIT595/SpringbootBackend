package com.manikanta.microservices.project.OrderService.Service.Implementation;

import com.manikanta.microservices.project.OrderService.DTO.OrderDTO;
import com.manikanta.microservices.project.OrderService.DTO.ProductDTO;
import com.manikanta.microservices.project.OrderService.Entity.Order;
import com.manikanta.microservices.project.OrderService.Publisher.OrderPublisher;
import com.manikanta.microservices.project.OrderService.Repository.OrderRepository;
import com.manikanta.microservices.project.OrderService.Service.FeignAPIClient;
import com.manikanta.microservices.project.OrderService.Service.OrderService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImplementation implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    FeignAPIClient feignAPIClient;

    @Autowired
    OrderPublisher orderPublisher;

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImplementation.class);

    @Autowired
    WebClient webClient;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public List<OrderDTO> getOrders() {
        List<OrderDTO> orders = orderRepository.findAll().stream().map(order -> mapToDTO(order)).collect(Collectors.toList());
        List<Long> orderIds = orders.stream().map(order -> order.getOrderId()).collect(Collectors.toList());
        List<ProductDTO> products = feignAPIClient.getProductsByOrderIds(orderIds);

        orders.forEach(order -> {
            // Filter products that match the current order ID
            List<ProductDTO> matchingProducts = products.stream()
                    .filter(product -> product.getOrderId().equals(order.getOrderId())) // Make sure getOrderId() matches your ProductDTO
                    .collect(Collectors.toList());

            // Set the matching products to the current order
            order.setProductsList(matchingProducts);
        });
        logger.info("Returning orders "+ orders);
        return orders;
//        orders.forEach( order -> {
//            Mono<List<ProductDTO>> products = webClient.get()
//                            .uri("http://localhost:8081/api/products/order/" + order.getOrderId())
//                            .retrieve()
//                            .bodyToMono(new ParameterizedTypeReference<>() {
//                            });
//            order.setProductsList(products.block());
    }

    @Override
    public OrderDTO getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).get();
        return mapToDTO(order);
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
        System.out.println("Order Deleted");
    }

    @Override
    public void addOrder(Order order) {
        orderRepository.save(order);
        System.out.println("Order Added");
    }

    @Override
    public List<OrderDTO> getOrderByUserId(Long userId) {
        logger.info("Inside getOrderByUserId");
        List<OrderDTO> orders = orderRepository.findByUserId(userId).stream().map(order -> mapToDTO(order)).collect(Collectors.toList());
        List<Long> orderIds = orders.stream().map(order -> order.getOrderId()).collect(Collectors.toList());
        List<ProductDTO> products = feignAPIClient.getProductsByOrderIds(orderIds);

        orders.forEach(order -> {
            // Filter products that match the current order ID
            List<ProductDTO> matchingProducts = products.stream()
                    .filter(product -> product.getOrderId().equals(order.getOrderId())) // Make sure getOrderId() matches your ProductDTO
                    .collect(Collectors.toList());

            // Set the matching products to the current order
            order.setProductsList(matchingProducts);
        });
        logger.info("Returning orders "+ orders);
        return orders;
    }

    @Override
    public OrderDTO getUsersOrders(String orderId) {
        return null;
    }

    @Override
    public String updateOrder(Order order) {
        Optional<Order> order1 = orderRepository.findById((long) order.getOrderId());
        if(!order1.isPresent()){
            throw new RuntimeException("Order Not Found");
        }
        if(order.getOrderStatus()=="Success" || order.getOrderStatus() == "Pending"){
            orderPublisher.publishOrderCreatedEvent(order);
        }
        else{
            orderPublisher.publishOrderCancelledEvent((long) order.getOrderId());
        }
        orderRepository.save(order);
        System.out.println("Order Updated");
        return "Order Updated";
    }

    public OrderDTO mapToDTO(Order order){
        return modelMapper.map(order,OrderDTO.class);
    }
}
