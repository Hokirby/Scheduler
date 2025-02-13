package com.example.SchedulingApp.Developed.domain.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateScheduleRequestDto {
    @NotBlank(message = "Title Must Be Entered")
    @Size(max = 10, message = "Title Is Available Within 10 Characters")
    private final String title;
    private final String content;
    @NotBlank(message = "MemberId Is Mandatory Entry")
    private final Long memberId;
}
