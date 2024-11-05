package com.manikanta.microservices.project.NotificationService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/notifications")
@AllArgsConstructor
public class NotificationController {

    @Autowired
    private EmailNotificationService emailNotificationService;

    @PostMapping("/send")
    public boolean sendEmailNotification(@RequestBody Notification notification) {
        return emailNotificationService.sendNotification(notification);
    }

}