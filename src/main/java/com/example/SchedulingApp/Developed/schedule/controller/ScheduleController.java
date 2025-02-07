package com.example.SchedulingApp.Developed.schedule.controller;

import com.example.SchedulingApp.Developed.schedule.service.ScheduleService;
import com.example.SchedulingApp.Developed.schedule.dto.CreateScheduleRequestDto;
import com.example.SchedulingApp.Developed.schedule.dto.ScheduleResponseDto;
import com.example.SchedulingApp.Developed.schedule.dto.UpdateScheduleRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    //일정 등록
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> saveSchedule(@RequestBody CreateScheduleRequestDto requestDto) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.saveSchedule(
                requestDto.getTitle(),
                requestDto.getContent(),
                requestDto.getMemberName()
        );
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }

    //전체 일정 조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedule() {
        List<ScheduleResponseDto> scheduleResponseDtoList = scheduleService.findAllSchedule();
        return new ResponseEntity<>(scheduleResponseDtoList, HttpStatus.OK);
    }

    //선택 일정 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id) {
        return new ResponseEntity<>(scheduleService.findScheduleById(id), HttpStatus.OK);
    }

    //선택 일정 수정
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long id, @RequestParam String password, @RequestBody UpdateScheduleRequestDto requestDto) throws MissingRequestValueException {
        return new ResponseEntity<>(scheduleService.updateSchedule(id, password, requestDto.getTitle(), requestDto.getMemberName()), HttpStatus.OK);
    }

    //선택 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteScheduleById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
