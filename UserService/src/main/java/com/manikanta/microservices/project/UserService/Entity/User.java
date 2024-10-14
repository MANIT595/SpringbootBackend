package com.manikanta.microservices.project.UserService.Entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Schema(
        description = "User Entity Model"
)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {

    @Schema(
            description = "User Id"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Schema(
            description = "User FirstName"
    )
    @NotEmpty(message = "firstName should not be null or empty")
    private String firstName;

    @Schema(
            description = "User LastName"
    )
    @NotEmpty(message = "lastName should not be null or empty")
    private String lastName;

    @Schema(
            description = "User Email"
    )
    @Column(nullable = false, unique = true)
    @NotEmpty(message = "email should not be null or empty")
    @Email
    private String email;

    @Schema(
            description = "User Password"
    )
    @Column(nullable = false)
    @NotEmpty(message = "password should not be null or empty")
    private String password;

    public User(String manikanta, String s, String s1, String mani) {
    }
}
