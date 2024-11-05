package com.manikanta.microservices.project.UserService.DTO;

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