package com.manikanta.microservices.project.UserService.Publisher;

import com.manikanta.microservices.project.UserService.DTO.Notification;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationPublisher {

    private final RabbitTemplate rabbitTemplate;

    private final String exchange = "notification_exchange";

    public NotificationPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishNotificationCreatedEvent(Notification notification) {
        rabbitTemplate.convertAndSend(exchange, "notification_created", notification);
        System.out.println("Published notification-created event: ");
    }

}

