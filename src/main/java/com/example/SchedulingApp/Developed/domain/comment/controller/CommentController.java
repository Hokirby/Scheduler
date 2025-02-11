package com.example.SchedulingApp.Developed.domain.comment.controller;

import com.example.SchedulingApp.Developed.domain.comment.dto.CommentRequestDto;
import com.example.SchedulingApp.Developed.domain.comment.dto.CommentResponseDto;
import com.example.SchedulingApp.Developed.domain.comment.service.CommentService;
import com.example.SchedulingApp.Developed.domain.comment.dto.UpdateCommentRequestDto;
import com.example.SchedulingApp.Developed.domain.member.dto.MemberResponseDto;
import com.example.SchedulingApp.Developed.domain.member.service.MemberService;
import com.example.SchedulingApp.Developed.domain.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/{scheduleId}")
@RestController
public class CommentController {

    private final CommentService commentService;
    private final MemberService memberService;
    private final ScheduleService scheduleService;

    //댓글 등록
    @PostMapping
    public ResponseEntity<CommentResponseDto> saveComment(@Valid @RequestBody CommentRequestDto requestDto) {
        MemberResponseDto responseDto = memberService.findByEmail(requestDto.getEmail());
        CommentResponseDto commentResponseDto = new CommentResponseDto(
                requestDto.getComment(),
                responseDto.getId()
        );
        return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);
    }

    //댓글 조회
    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> findAllComment() {
        List<CommentResponseDto> comments = commentService.findAllComment();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    //댓글 수정
    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long id, @RequestBody UpdateCommentRequestDto requestDto) {
        return new ResponseEntity<>(commentService.updateComment(
                id,
                requestDto.getComment(),
                requestDto.getEmail(),
                requestDto.getPassword()
        ), HttpStatus.OK);
    }

    //댓글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id, @RequestBody UpdateCommentRequestDto requestDto) {
        commentService.deleteComment(id, requestDto.getComment(), requestDto.getEmail(), requestDto.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
