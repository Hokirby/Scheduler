package com.example.SchedulingApp.Developed.domain.comment.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentRequestDto {
    @NotBlank(message = "Comment Must Be Entered")
    private String comment;
    @NotBlank(message = "Email Must Be Entered")
    @Email
    private String email;
}
