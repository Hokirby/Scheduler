package com.example.SchedulingApp.Developed.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public record SignUpResponseDto(Long id, String memberName, String email) {
}
