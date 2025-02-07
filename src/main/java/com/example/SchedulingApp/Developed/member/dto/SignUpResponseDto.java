package com.example.SchedulingApp.Developed.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpResponseDto {
    private final Long id;
    private final String memberName;
    private final String email;
}
