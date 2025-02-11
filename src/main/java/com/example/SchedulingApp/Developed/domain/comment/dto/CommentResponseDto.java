package com.example.SchedulingApp.Developed.domain.comment.dto;

import com.example.SchedulingApp.Developed.domain.comment.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private final String comment;
    private final Long memberId;

    public CommentResponseDto(String comment, Long memberId) {
        this.comment = comment;
        this.memberId = memberId;
    }

    public static CommentResponseDto toDto(Comment comment) {
        return new CommentResponseDto(comment.getComment(), comment.getMember().getId());
    }
}
