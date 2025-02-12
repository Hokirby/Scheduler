package com.example.SchedulingApp.Developed.domain.comment.service;

import com.example.SchedulingApp.Developed.config.PasswordEncoder;
import com.example.SchedulingApp.Developed.domain.comment.dto.CommentResponseDto;
import com.example.SchedulingApp.Developed.domain.comment.entity.Comment;
import com.example.SchedulingApp.Developed.domain.comment.repository.CommentRepository;
import com.example.SchedulingApp.Developed.domain.member.entity.Member;
import com.example.SchedulingApp.Developed.domain.member.repository.MemberRepository;
import com.example.SchedulingApp.Developed.domain.schedule.entity.Schedule;
import com.example.SchedulingApp.Developed.domain.schedule.repository.ScheduleRepository;
import com.example.SchedulingApp.Developed.exception.ApplicationException;
import com.example.SchedulingApp.Developed.exception.ErrorMessageCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    //댓글 생성
    @Transactional
    public CommentResponseDto createComment(String comment, Long scheduleId, Long memberId) {
        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(scheduleId);
        Member member = memberRepository.findMemberByIdOrElseThrow(memberId);
        Comment commentEntity = new Comment(comment, member, schedule);
        commentRepository.save(commentEntity);
        schedule.setCommentCount(schedule.getCommentCount() + 1);
        return new CommentResponseDto(commentEntity.getComment(), commentEntity.getMember().getId(), commentEntity.getSchedule().getId());
    }

    //댓글 목록 조회
    public List<CommentResponseDto> findAllComment() {
        return commentRepository.findAll().stream()
                .map(CommentResponseDto::toDto).toList();
    }

    //댓글 수정
    public CommentResponseDto updateComment(Long id, String comment, String email, String rawPassword) {
        validateAndAuthenticate(comment, email, rawPassword);
        Comment foundComment = commentRepository.findCommentByIdOrElseThrow(id);
        foundComment.setComment(comment);
        commentRepository.save(foundComment);
        return CommentResponseDto.toDto(foundComment);
    }

    //댓글 삭제
    public void deleteComment(Long scheduleId, Long id, String comment, String email, String rawPassword) {
        validateAndAuthenticate(comment, email, rawPassword);
        Comment foundComment = commentRepository.findCommentByIdOrElseThrow(id);
        commentRepository.delete(foundComment);
        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(scheduleId);
        schedule.setCommentCount(schedule.getCommentCount() - 1);
    }

    //필수값 인증 및 검증
    private void validateAndAuthenticate(String comment, String email, String rawPassword) {
        if (comment.isBlank() || email.isBlank()) {
            throw new ApplicationException(ErrorMessageCode.BAD_REQUEST, "Check The Mandatory Entry(comment, email)");
        }
        Member foundMember = memberRepository.findMemberByEmailOrElseThrow(email);
        String encodedPassword = passwordEncoder.encode(rawPassword);
        if (foundMember.getPassword().equals(encodedPassword)){
            throw new ApplicationException(ErrorMessageCode.UNAUTHORIZED, "Password Doesn't Match");
        }
    }

}
