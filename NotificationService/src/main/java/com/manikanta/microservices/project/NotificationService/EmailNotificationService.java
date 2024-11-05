package com.manikanta.microservices.project.NotificationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService {

    @Autowired
    private JavaMailSender mailSender;

    private static final Logger logger = LoggerFactory.getLogger(EmailNotificationService.class);

    public boolean sendNotification(Notification notification){
        logger.info("Inside Notification Controller");
        try {
            // Prepare the email message
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(notification.getRecipient());
            message.setSubject(notification.getSubject());
            message.setText(notification.getMessage());

            // Send the email
            mailSender.send(message);

            // Update notification status after successful send
//            notification.setIsSent(true);
        } catch (Exception e) {
            System.out.println("Error while sending email: " + e.getMessage());
        }
        return true;
    }
}
