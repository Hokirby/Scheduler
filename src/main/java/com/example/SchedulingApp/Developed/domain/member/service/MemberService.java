package com.example.SchedulingApp.Developed.domain.member.service;

import com.example.SchedulingApp.Developed.config.PasswordEncoder;
import com.example.SchedulingApp.Developed.config.SessionUtil;
import com.example.SchedulingApp.Developed.domain.member.dto.MemberResponseDto;
import com.example.SchedulingApp.Developed.domain.member.entity.Member;
import com.example.SchedulingApp.Developed.domain.member.repository.MemberRepository;
import com.example.SchedulingApp.Developed.exception.ApplicationException;
import com.example.SchedulingApp.Developed.exception.ErrorMessageCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final SessionUtil sessionUtil;

    //Id로 회원 조회
    @Transactional
    public MemberResponseDto findById(HttpServletRequest request, Long id) {
        Long userId = sessionUtil.findUserIdBySession(request);
        if (userId != id) {
            throw new ApplicationException(ErrorMessageCode.UNAUTHORIZED, "There Is No Member Logged In");
        }
        Member foundMember = memberRepository.findMemberByIdOrElseThrow(id);
        return new MemberResponseDto(foundMember.getId(), foundMember.getName());
    }

    //비밀번호 수정
    @Transactional
    public void updatePassword(HttpServletRequest request, Long id, String oldPassword, String newPassword) {
        Long userId = sessionUtil.findUserIdBySession(request);
        if (userId != id) {
            throw new ApplicationException(ErrorMessageCode.UNAUTHORIZED, "There Is No Member Logged In");
        }
        Member foundMember = memberRepository.findMemberByIdOrElseThrow(id);
        String encodedOldPassWord = passwordEncoder.encode(oldPassword);
        if (!passwordEncoder.matches(encodedOldPassWord, foundMember.getPassword())) {
            throw new ApplicationException(ErrorMessageCode.UNAUTHORIZED, "Password Doesn't Match");
        }
        String encodedNewPassword = passwordEncoder.encode(newPassword);
        foundMember.updatePassword(encodedNewPassword);
    }

    //회원정보 삭제
    @Transactional
    public void delete(HttpServletRequest request, Long id, String rawPassword) {
        Long userId = sessionUtil.findUserIdBySession(request);
        if (userId != id) {
            throw new ApplicationException(ErrorMessageCode.UNAUTHORIZED, "There Is No Member Logged In");
        }
        Member foundMember = memberRepository.findMemberByIdOrElseThrow(id);
        String encodedPassword = passwordEncoder.encode(rawPassword);
        if (!passwordEncoder.matches(encodedPassword, foundMember.getPassword())) {
            throw new ApplicationException(ErrorMessageCode.UNAUTHORIZED, "Password Doesn't Match");
        }
        memberRepository.delete(foundMember);
    }

    //email 로 멤버 조회
    public MemberResponseDto findByEmail(String email) {
        Member foundMember = memberRepository.findMemberByEmailOrElseThrow(email);
        return new MemberResponseDto(foundMember.getId(), foundMember.getName());
    }

}
