package com.velikanovdev.sportcenterplatform.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UserDTO(
        @NotBlank(message = "Email is required") @Email(message = "Email is not valid") String email,
        @NotBlank(message = "Username is required") String username,
        @NotBlank(message = "Password is required") String password
) {}