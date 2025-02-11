package com.example.SchedulingApp.Developed.domain.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateScheduleRequestDto {
    private final Long id;
    private final String title;
    private final String email;
    private final String password;
}
