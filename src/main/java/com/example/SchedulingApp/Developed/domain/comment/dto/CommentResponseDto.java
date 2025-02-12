package com.example.SchedulingApp.Developed.domain.comment.dto;

import com.example.SchedulingApp.Developed.domain.comment.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private final String comment;
    private final Long memberId;
    private final Long scheduleId;

    public CommentResponseDto(String comment, Long memberId, Long scheduleId) {
        this.comment = comment;
        this.memberId = memberId;
        this.scheduleId = scheduleId;
    }

    public static CommentResponseDto toDto(Comment comment) {
        return new CommentResponseDto(comment.getComment(), comment.getMember().getId(), comment.getSchedule().getId());
    }
}
