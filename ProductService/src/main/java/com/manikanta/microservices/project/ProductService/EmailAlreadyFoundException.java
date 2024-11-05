package com.manikanta.microservices.project.ProductService;

public class EmailAlreadyFoundException extends RuntimeException {
    public EmailAlreadyFoundException(String message){
        super(message);
    }
}
