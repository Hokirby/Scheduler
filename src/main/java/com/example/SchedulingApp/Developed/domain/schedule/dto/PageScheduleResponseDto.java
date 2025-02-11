package com.example.SchedulingApp.Developed.domain.schedule.dto;

import com.example.SchedulingApp.Developed.domain.schedule.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PageScheduleResponseDto {
    private String title;
    private String content;
    private int commentCount;
    private String memberName;

    //entity 를 Dto 로 변환
    public static PageScheduleResponseDto toDto(Schedule schedule) {
        return new PageScheduleResponseDto(schedule.getTitle(), schedule.getContent(), schedule.getComments().size(), schedule.getMember().getName());
    }
}
