package com.task.task_management.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @NotBlank(message = "User name is required")
    private String username;
    @NotBlank(message = "Password is required")
    private String password;
}
