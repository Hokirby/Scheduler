package com.example.SchedulingApp.Developed.domain.schedule.dto;

import com.example.SchedulingApp.Developed.domain.comment.entity.Comment;
import com.example.SchedulingApp.Developed.domain.schedule.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@AllArgsConstructor
public class PageScheduleResponseDto {
    private String title;
    private String content;
    private int commentCount;
    private String memberName;
    @Setter
    private Page<Comment> commentPage;
}
