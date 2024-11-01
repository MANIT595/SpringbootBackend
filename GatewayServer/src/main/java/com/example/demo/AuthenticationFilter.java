package com.example.demo;

import com.example.demo.Exception.EmailAlreadyFoundException;
import com.example.demo.Exception.ServiceCustomException;
import com.example.demo.Exception.UserNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ObjectMapper objectMapper;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                //header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    return onError(exchange, "No Token Provided", HttpStatus.UNAUTHORIZED);
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {
                    jwtUtil.validateToken(authHeader);
                } catch (EmailAlreadyFoundException e) {
                    return onError(exchange, e.getMessage(), HttpStatus.FORBIDDEN);
                } catch (ServiceCustomException e) {
                    return onError(exchange, e.getMessage(), HttpStatus.NOT_FOUND);
                } catch (Exception e) {
                    return onError(exchange, "Unauthorized access to application", HttpStatus.UNAUTHORIZED);
                }
            }

            return chain.filter(exchange)
                    .onErrorResume(throwable -> {
                        // Handle specific exception types
                        if (throwable instanceof UserNotFoundException) {
                            return onError(exchange, throwable.getMessage(), HttpStatus.UNAUTHORIZED);
                        }
                        // Handle generic exceptions
                        return onError(exchange, "An error occurred: ", HttpStatus.UNAUTHORIZED);
                    });
        });
    }

    private Mono<Void> onError(ServerWebExchange exchange, String errorMessage, HttpStatus status) {
        System.out.println("in on error");
        ErrorDTO errorResponse = new ErrorDTO(
                LocalDateTime.now(),
                errorMessage,
                exchange.getRequest().getURI().getPath(),
                status.getReasonPhrase(), // Set error code from HTTP status
                status.value()
        );

        exchange.getResponse().setStatusCode(status);
        exchange.getResponse().getHeaders().add(HttpHeaders.CONTENT_TYPE, "application/json");

        try {
            // Write the error response
            byte[] bytes = objectMapper.writeValueAsBytes(errorResponse);
            return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(bytes)));
        } catch (Exception e) {
            return exchange.getResponse().setComplete(); // In case of an error while writing the response
        }
    }

    public static class Config {

    }
}

import com.example.demo.Exception.GlobalExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Order(-1)
public class AuthenticationFilter implements GlobalFilter, Ordered {

    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    GlobalExceptionHandler globalExceptionHandler;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        System.out.println("Incoming request: " + request.getURI());

        if (validator.isSecured.test(request)) {
            System.out.println("Request is secured. Checking authorization...");

            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                throw new RuntimeException("Missing authorization header");
            }

            String authHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                authHeader = authHeader.substring(7); // Extract token
            } else {
                throw new RuntimeException("Missing authorization header");
            }

            try {
                jwtUtil.validateToken(authHeader);
                System.out.println("Token is valid");
            } catch (Exception e) {
                throw new RuntimeException("Missing authorization header");
            }
        } else {
            System.out.println("Request is open, no authorization required");
        }

        return chain.filter(exchange);  // Proceed with the request
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
