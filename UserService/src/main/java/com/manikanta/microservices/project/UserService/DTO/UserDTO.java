package com.manikanta.microservices.project.UserService.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Schema(
        description = "UserDTO Schema"
)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable{
    @Schema(
            description = "User ID"
    )
    private Long userId;

    @Schema(
            description = "User FirstName"
    )
    private String firstName;

    @Schema(
            description = "User LastName"
    )
    private String lastName;

    @Schema(
            description = "User Email"
    )
    @Column(nullable = false, unique = true)
    private String email;
}