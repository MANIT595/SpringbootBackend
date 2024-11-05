package com.manikanta.microservices.project.NotificationService.Config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String EXCHANGE_NAME = "notification_exchange";
    public static final String NOTIFICATION_CREATED_QUEUE = "notification_created_queue";

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public TopicExchange notificationExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    // Declare the notification created queue
    @Bean
    public Queue notificationCreatedQueue() {
        return new Queue(NOTIFICATION_CREATED_QUEUE, true);  // Durable queue
    }

    @Bean
    public Binding bindingNotification(Queue notificationCreatedQueue, TopicExchange orderExchange) {
        return BindingBuilder.bind(notificationCreatedQueue).to(orderExchange).with("notification_created");
    }
}

