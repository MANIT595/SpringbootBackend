package com.manikanta.microservices.project.Gateway;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.server.WebFilter;
//import org.springframework.web.server.WebFilterChain;
//import reactor.core.publisher.Mono;
//
//@Component
//public class JwtAuthenticationFilter implements WebFilter {
//
//    @Autowired
//    private JWTService jwtService;
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
//        ServerHttpRequest request = exchange.getRequest();
//
//        // Check if the Authorization header exists
//        if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
//            return chain.filter(exchange); // No Authorization header, continue to the next filter
//        }
//
//        String authorizationHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
//        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
//            return chain.filter(exchange); // Invalid header, continue to the next filter
//        }
//
//        String token = authorizationHeader.substring(7); // Remove "Bearer " from the token
//
//        // Validate the JWT Token
//        if (!jwtService.validateToken(token)) {
//            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//            return exchange.getResponse().setComplete(); // Complete the response without further processing
//        }
//
//        System.out.println("Valid token, continuing to next filter.");
//
//        return chain.filter(exchange)
//                .switchIfEmpty(Mono.error(new RuntimeException("The Mono returned by the supplier is null")));
//    }
//}

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter  {

    @Autowired
    private JWTService jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                jwtUtil.validateToken(token);  // Validate the token
            } catch (Exception e) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                return; // Stop the request processing if the token is invalid
            }
        }

        filterChain.doFilter(request, response); // Continue the request chain
    }
}
