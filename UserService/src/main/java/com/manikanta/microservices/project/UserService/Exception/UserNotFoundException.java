package com.manikanta.microservices.project.UserService.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserNotFoundException extends RuntimeException {
    String message;
    public UserNotFoundException(String message){
        super(message);
    }
}

