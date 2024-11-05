package com.example.demo;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDTO {

    private LocalDateTime timestamp;

    private String message;

    private String path;

    private String errorCode;

    private Integer responseCode;
}
