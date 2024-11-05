package com.manikanta.microservices.project.NotificationService;

import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    private String recipient;
    private String message;
    private String subject;

    // Constructors, Getters, Setters
}