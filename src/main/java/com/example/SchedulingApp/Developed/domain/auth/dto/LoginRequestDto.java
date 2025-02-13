package com.example.SchedulingApp.Developed.domain.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequestDto {
    @Email
    @NotBlank(message = "Email Must Be Entered")
    private final String email;
    @NotBlank(message = "Password Must BE Entered")
    private final String password;
}
