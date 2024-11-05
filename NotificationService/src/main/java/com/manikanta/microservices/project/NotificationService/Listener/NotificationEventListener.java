package com.manikanta.microservices.project.NotificationService.Listener;

import com.manikanta.microservices.project.NotificationService.EmailNotificationService;
import com.manikanta.microservices.project.NotificationService.Notification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NotificationEventListener {

    @Autowired
    private EmailNotificationService emailNotificationService;

    @RabbitListener(queues = "notification_created_queue")
    public void handleOrderEvents(Notification notification) {
        System.out.println("In handleOrderEvents");
        emailNotificationService.sendNotification(notification);
            System.out.println("Notification Sent Successfully");
    }
}

