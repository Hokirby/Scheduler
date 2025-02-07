package com.example.SchedulingApp.Developed.domain.login.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequestDto {
    private final String memberName;
    private final String password;
}
