package com.manikanta.microservices.project.ProductService.Service.Implementation;

import com.manikanta.microservices.project.ProductService.Entity.Product;
import com.manikanta.microservices.project.ProductService.Repository.ProductRepository;
import com.manikanta.microservices.project.ProductService.Service.ProductService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImplementation implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImplementation.class);

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    public Product getProduct(Long productId) {
        Product product = productRepository.findById(productId).get();
        return product;
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
        System.out.println("Product deleted");
    }

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
        System.out.println("Product Added");
    }

    @Override
    public void updateQuantity(Long productId, String status) {
        Optional<Product> product = productRepository.findById(productId);
        if(product.isPresent()){
            long quantity = product.get().getQuantity();
            if(status=="Success" || status == "Pending"){
                productRepository.updateQuantityByProductId(productId, quantity-1);
            }
            else {
                productRepository.updateQuantityByProductId(productId, quantity+1);
            }
        }
        else{
            System.out.println("No Product Available with this product ID");
        }

    }

    @Override
    public List<Product> getProductsByOrderId(List<Long> orderIds) {
        logger.info("Inside getProductsByOrderId");
        List<Product> products = productRepository.findProductsByOrderIds(orderIds);
        logger.info("Returning products "+ products);
        products.forEach((productDTO -> logger.info("product id ="+productDTO.getBrand())));
        return products;
    }
}
