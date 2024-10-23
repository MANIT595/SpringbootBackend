package com.manikanta.microservices.project.UserService.DTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WeblabDTO{

    private Long weblabId;

    private String weblabName;

    private Long weblabPercentage;
}