package com.manikanta.microservices.project.UserService.FeignClients;

import com.manikanta.microservices.project.UserService.DTO.Notification;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service")
public interface FeignNotificationClient {
    @GetMapping("api/notifications/send")
    boolean sendNotification(@RequestBody Notification notification);
}