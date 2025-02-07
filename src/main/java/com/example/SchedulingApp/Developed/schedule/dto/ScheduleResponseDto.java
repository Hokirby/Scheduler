package com.example.SchedulingApp.Developed.schedule.dto;

import com.example.SchedulingApp.Developed.schedule.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {
    private final Long id;
    private final String title;
    private final String content;

    //entity 를 Dto 로 변환
    public static ScheduleResponseDto toDto(Schedule schedule) {
        return new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getContent());
    }
}
