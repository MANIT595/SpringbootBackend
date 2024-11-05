package com.manikanta.microservices.project.UserService.Exception;


public class CustomServiceException  extends RuntimeException{
    public CustomServiceException(String message){
        super(message);
    }
}
