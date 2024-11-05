package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class LoggingFilter extends AbstractGatewayFilterFactory<LoggingFilter.Config> {

    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    public LoggingFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            // Log the request
            logger.info("Request: {} {}", exchange.getRequest().getMethod(), exchange.getRequest().getURI());

            return chain.filter(exchange)
                    .doOnSuccess(aVoid -> {
                        // Log the response status
                        logger.info("Response Status: {}", exchange.getResponse().getStatusCode());
                    });
        };
    }

    public static class Config {
        // Add any configuration properties if needed
    }
}
