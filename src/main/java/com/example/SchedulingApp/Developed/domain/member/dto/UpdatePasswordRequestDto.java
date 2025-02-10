package com.example.SchedulingApp.Developed.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdatePasswordRequestDto {
    @NotBlank(message = "Please Enter Your Existing Password")
    private final String oldPassword;
    @NotBlank(message = "Please Enter A Password To Change")
    private final String newPassword;

    public UpdatePasswordRequestDto(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
