package com.example.SchedulingApp.Developed.domain.schedule.controller;

import com.example.SchedulingApp.Developed.domain.schedule.dto.CreateScheduleRequestDto;
import com.example.SchedulingApp.Developed.domain.schedule.dto.PageScheduleResponseDto;
import com.example.SchedulingApp.Developed.domain.schedule.dto.ScheduleResponseDto;
import com.example.SchedulingApp.Developed.domain.schedule.dto.UpdateScheduleRequestDto;
import com.example.SchedulingApp.Developed.domain.schedule.service.ScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    //일정 등록
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> saveSchedule(@Valid @RequestBody CreateScheduleRequestDto requestDto) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.saveSchedule(
                requestDto.getTitle(),
                requestDto.getContent(),
                requestDto.getMemberId()
        );
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }

    //전체 일정 조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedule() {
        List<ScheduleResponseDto> schedules = scheduleService.findAll();
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    //선택 일정 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id) {
        return new ResponseEntity<>(scheduleService.findScheduleById(id), HttpStatus.OK);
    }

    //선택 댓글 조회 + 페이징 기능(페이지 크기 10, 수정일 기준 내림차순)
    @GetMapping("/{id}/comment")
    public ResponseEntity<Page<PageScheduleResponseDto>> getComments(@PathVariable Long id,
                                                                     @RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("modifiedAt").descending());
        Page<PageScheduleResponseDto> commentPage = scheduleService.getComments(id, pageable);
        return new ResponseEntity<>(commentPage, HttpStatus.OK);
    }

    //선택 일정 수정
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long id, HttpServletRequest httpServletRequest, @Valid @RequestBody UpdateScheduleRequestDto requestDto) {
        return new ResponseEntity<>(scheduleService.updateSchedule(httpServletRequest, id, requestDto.getPassword(), requestDto.getTitle(), requestDto.getEmail()), HttpStatus.OK);
    }

    //선택 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id, HttpServletRequest httpServletRequest, @Valid @RequestBody UpdateScheduleRequestDto requestDto) {
        scheduleService.deleteScheduleById(httpServletRequest, id, requestDto.getTitle(), requestDto.getEmail(), requestDto.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
