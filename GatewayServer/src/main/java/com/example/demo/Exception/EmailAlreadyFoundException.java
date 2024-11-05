package com.example.demo.Exception;

public class EmailAlreadyFoundException extends RuntimeException {
    public EmailAlreadyFoundException(String message){
        super(message);
    }
}
