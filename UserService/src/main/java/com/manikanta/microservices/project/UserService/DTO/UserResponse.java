package com.manikanta.microservices.project.UserService.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Schema(
        description = "UserResponse Model"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    @Schema(
            description = "Users"
    )
    private List<UserDTO> Users;

    @Schema(
            description = "Page No"
    )
    private int pageNo;

    @Schema(
            description = "Page Size"
    )
    private int pageSize;

    @Schema(
            description = "Total Elements"
    )
    private long totalElements;

    @Schema(
            description = "Total Pages"
    )
    private int totalPages;

    @Schema(
            description = "Is Last Page"
    )
    private boolean last;
}
