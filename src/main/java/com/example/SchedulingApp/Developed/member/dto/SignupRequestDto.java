package com.example.SchedulingApp.Developed.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignupRequestDto {

    private final String memberName;
    private final String password;
    private final String email;
}
