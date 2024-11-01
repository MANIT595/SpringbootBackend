package com.manikanta.microservices.project.UserService.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailAlreadyFoundException extends RuntimeException {
    String message;
    public EmailAlreadyFoundException(String message){
        super(message);
    }
}
