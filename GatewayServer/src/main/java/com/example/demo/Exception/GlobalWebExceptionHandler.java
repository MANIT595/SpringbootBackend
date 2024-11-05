package com.example.demo.Exception;

import com.example.demo.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalWebExceptionHandler {}
//    @ExceptionHandler(UserNotFoundException.class)
//    public ResponseEntity<?> handleOrderNotFoundException(UserNotFoundException ex) {
//        ErrorDTO errorResponse = ErrorDTO.builder()
//                .timestamp(LocalDateTime.now())
//                .message(ex.getMessage())
//                .errorCode(String.valueOf(404))
//                .responseCode(HttpStatus.NO_CONTENT.value())
//                .build();
//        return ResponseEntity.internalServerError().body(errorResponse);
//    }
//}
//
//
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.server.WebExceptionHandler;
//import reactor.core.publisher.Mono;
//
//@Component
//@Order(-2) // Ensure it runs early in the exception handling chain
//public class GlobalWebExceptionHandler implements WebExceptionHandler {
//
//    @Override
//    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
//        HttpStatus status;
//        String errorMessage;
//
//        if (ex instanceof UserNotFoundException || ex instanceof EmailAlreadyFoundException || ex instanceof Exception ) { // Replace with specific exceptions as needed
//            System.out.println("INSIDE");
//            status = HttpStatus.CONFLICT; // Set to the status you want
//            errorMessage = ex.getMessage();
//        } else {
//            status = HttpStatus.INTERNAL_SERVER_ERROR;
//            errorMessage = "Internal Server Error";
//        }
//
//        exchange.getResponse().setStatusCode(status);
//        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
//
//        String jsonErrorResponse = String.format(
//                "{\"timestamp\":\"%s\", \"status\":%d, \"error\":\"%s\"}",
//                java.time.LocalDateTime.now(),
//                status.value(),
//                errorMessage
//        );
//
//        return exchange.getResponse()
//                .writeWith(Mono.just(exchange.getResponse()
//                        .bufferFactory()
//                        .wrap(jsonErrorResponse.getBytes())));
//    }
//}
