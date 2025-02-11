package com.example.SchedulingApp.Developed.domain.comment.dto;

import lombok.Getter;

@Getter
public class UpdateCommentRequestDto {
    private final String comment;
    private final String email;
    private final String password;

    public UpdateCommentRequestDto(String comment, String email, String password) {
        this.comment = comment;
        this.email = email;
        this.password = password;
    }
}
