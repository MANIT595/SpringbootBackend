package com.manikanta.microservices.project.OrderService.Service;

import com.manikanta.microservices.project.OrderService.DTO.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@FeignClient(url = "http://localhost:8082", value = "order-service")
@FeignClient(name = "product-service")
public interface FeignAPIClient {
    @PostMapping("api/products/order")
    List<ProductDTO> getProductsByOrderIds(@RequestBody() List<Long> orderIds);
}

