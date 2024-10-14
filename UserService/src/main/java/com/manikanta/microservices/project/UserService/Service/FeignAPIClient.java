package com.manikanta.microservices.project.UserService.Service;


import com.manikanta.microservices.project.UserService.DTO.OrderDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//@FeignClient(url = "http://localhost:8082", value = "order-service")
@FeignClient(name = "order-service")
public interface FeignAPIClient {
    @GetMapping("api/orders/user/{user-id}")
    List<OrderDTO> getOrdersByUserId(@PathVariable("user-id") Long userId);
}
