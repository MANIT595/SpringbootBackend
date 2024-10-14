package com.manikanta.microservices.project.ProductService.Controller;

import com.manikanta.microservices.project.ProductService.DTO.ProductDTO;
import com.manikanta.microservices.project.ProductService.Entity.Product;
import com.manikanta.microservices.project.ProductService.Service.ProductService;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class ProductController {

    @Autowired
    private ProductService productService;

    private MeterRegistry meterRegistry;

    @GetMapping()
    public List<Product> getProducts() {
        return productService.getProducts();

    }

    @GetMapping("{product-id}")
    public Product getProductById(@PathVariable("product-id") Long id) {
    return productService.getProduct(id);
    }

    @PostMapping("/order")
    public List<Product> getProductByOrderId(@RequestBody() List<Long> orderIds) {
        System.out.println("In Get Mapping getProductByOrderId");
        meterRegistry.counter("PRODUCTS_getProductByOrderId").increment();
        return productService.getProductsByOrderId(orderIds);
    }

    @DeleteMapping("{product-id}")
    public void deleteUserById(@PathVariable("user-id") Long id) {
        productService.deleteProduct(id);
    }

    @PostMapping
    public void addProduct(@RequestBody Product product){
        productService.addProduct(product);
    }
}