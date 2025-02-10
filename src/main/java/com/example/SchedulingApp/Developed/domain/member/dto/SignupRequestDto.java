package com.example.SchedulingApp.Developed.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignupRequestDto {
    @NotBlank(message = "Name Must Be Entered")
    @Size(max = 4, message = "Name Is Available Within 4 Characters")
    private final String memberName;
    @NotBlank(message = "Password Must Be Entered")
    private final String password;
    @Email
    @NotBlank(message = "Password Must Be Entered")
    private final String email;
}
