package com.example.SchedulingApp.Developed.domain.schedule.service;

import com.example.SchedulingApp.Developed.config.PasswordEncoder;
import com.example.SchedulingApp.Developed.config.SessionUtil;
import com.example.SchedulingApp.Developed.domain.member.entity.Member;
import com.example.SchedulingApp.Developed.domain.member.repository.MemberRepository;
import com.example.SchedulingApp.Developed.domain.schedule.dto.PageScheduleResponseDto;
import com.example.SchedulingApp.Developed.domain.schedule.dto.ScheduleResponseDto;
import com.example.SchedulingApp.Developed.domain.schedule.entity.Schedule;
import com.example.SchedulingApp.Developed.domain.schedule.repository.ScheduleRepository;
import com.example.SchedulingApp.Developed.exception.ApplicationException;
import com.example.SchedulingApp.Developed.exception.ErrorMessageCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final SessionUtil sessionUtil;

    //회원 정보 저장
    @Transactional
    public ScheduleResponseDto saveSchedule(String title, String content, Long memberId) {
        //작성자 id 로 작성자 찾기
        Member foundMember = memberRepository.findMemberByIdOrElseThrow(memberId);
        //새로운 일정 객체 생성
        Schedule schedule = new Schedule(title, content);
        //일정에 작성자 정보 설정
        schedule.setMember(foundMember);
        //데이터베이스에 일정 저장
        scheduleRepository.save(schedule);
        return new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getContent());
    }

    //전체 일정 조회
    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> findAll() {
        return scheduleRepository.findAll().stream().map(ScheduleResponseDto::toDto).toList();
    }

    //일정 id 에 따라 댓글 모두 가져오기
    @Transactional(readOnly = true)
    public Page<PageScheduleResponseDto> getComments(Long scheduleId, Pageable pageable) {
        return scheduleRepository.findAllByOrderByScheduleIdModifiedAtDesc(scheduleId, pageable);
    }

    // 일정 id로 일정 조회
    @Transactional(readOnly = true)
    public ScheduleResponseDto findScheduleById(Long id) {
        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);
        return ScheduleResponseDto.toDto(schedule);
    }

    //일정 id와 작성자 이름 , 비밀번호로 일정 수정
    @Transactional
    public ScheduleResponseDto updateSchedule(HttpServletRequest request, Long id, String title, String email, String rawPassword) {
        validateAndAuthenticate(request, title, email, rawPassword);
        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);
        schedule.setTitle(title);
        scheduleRepository.save(schedule);
        return ScheduleResponseDto.toDto(schedule);
    }


    //id로 일정 삭제
    @Transactional
    public void deleteScheduleById(HttpServletRequest request, Long id, String title, String email, String rawPassword) {
        validateAndAuthenticate(request, title, email, rawPassword);
        Schedule foundSchedule = scheduleRepository.findScheduleByIdOrElseThrow(id);
        scheduleRepository.delete(foundSchedule);
    }

    //필수값 검증 및 인증
    private void validateAndAuthenticate(HttpServletRequest request, String title, String email, String rawPassword) {
        if (title == null || email == null) {
            throw new ApplicationException(ErrorMessageCode.BAD_REQUEST, "Check The Mandatory Entry(title, email)");
        }
        Long userId = sessionUtil.findUserIdBySession(request);
        Member foundMember = memberRepository.findMemberByEmailOrElseThrow(email);
        if (userId != foundMember.getId()) {
            throw new ApplicationException(ErrorMessageCode.UNAUTHORIZED, "There Is No Member Logged In");
        }
        String encodedPassword = passwordEncoder.encode(rawPassword);
        if (foundMember.getPassword().equals(encodedPassword)) {
            throw new ApplicationException(ErrorMessageCode.UNAUTHORIZED, "Password Doesn't Match");
        }

    }

}
