package com.manikanta.microservices.project.ProductService.Service.Implementation;

import com.manikanta.microservices.project.ProductService.EmailAlreadyFoundException;
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
        logger.info("inside getProducts method");
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    public Product getProduct(Long productId) {
        logger.info("inside getProduct method");
        Product product = productRepository.findById(productId).orElseThrow(()-> new EmailAlreadyFoundException("In products"));
        return product;
    }

    @Override
    public void deleteProduct(Long productId) {
        logger.info("inside deleteProduct method");
        productRepository.deleteById(productId);
    }

    @Override
    public void addProduct(Product product) {
        logger.info("inside addProduct method");
        productRepository.save(product);
    }

    @Override
    public void updateQuantity(Long productId, String status) {
        logger.info("inside updateQuantity method");
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
            logger.info("No Product Available with this product ID");
        }

    }

    @Override
    public List<Product> getProductsByOrderId(List<Long> orderIds) {
        logger.info("inside getProductsByOrderId");
        List<Product> products = productRepository.findProductsByOrderIds(orderIds);
        return products;
    }

    @Override
    public List<Product> getProductsByCriteria(String brand, String productDesc, String productName, Long quantity) {
        logger.info("inside getProductsByCriteria");
        return productRepository.findProductsByCriteria(brand,productDesc,productName,quantity);
    }
}
