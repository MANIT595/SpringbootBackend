package com.manikanta.microservices.project.WebLabService.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "weblabs")
public class Weblab implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long weblabId;

    @Column(nullable = false, unique = true)
    private String weblabName;

    @Column(nullable = false)
    private Long weblabPercentage;
}
