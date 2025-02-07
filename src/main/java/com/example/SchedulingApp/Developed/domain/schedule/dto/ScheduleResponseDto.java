package com.example.SchedulingApp.Developed.domain.schedule.dto;

import com.example.SchedulingApp.Developed.domain.schedule.entity.Schedule;

public record ScheduleResponseDto(Long id, String title, String content) {
    //entity 를 Dto 로 변환
    public static ScheduleResponseDto toDto(Schedule schedule) {
        return new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getContent());
    }
}
