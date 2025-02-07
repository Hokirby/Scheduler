package com.example.SchedulingApp.Developed.domain.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateScheduleRequestDto {
    private final String title;
    private final String content;
    private final String memberName;
}
