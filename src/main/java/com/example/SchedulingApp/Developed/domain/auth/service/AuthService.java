package com.example.SchedulingApp.Developed.domain.auth.service;

import com.example.SchedulingApp.Developed.config.PasswordEncoder;
import com.example.SchedulingApp.Developed.domain.auth.dto.LoginResponseDto;
import com.example.SchedulingApp.Developed.domain.member.dto.SignUpResponseDto;
import com.example.SchedulingApp.Developed.domain.member.entity.Member;
import com.example.SchedulingApp.Developed.domain.member.repository.MemberRepository;
import com.example.SchedulingApp.Developed.exception.ApplicationException;
import com.example.SchedulingApp.Developed.exception.ErrorMessageCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    //암호화된 비밀번호로 회원 정보 저장
    @Transactional
    public SignUpResponseDto signUp(String memberName, String rawPassword, String email) {
        String encodedPassword = passwordEncoder.encode(rawPassword);
        Member member = new Member(memberName, encodedPassword, email);
        Member savedMember = memberRepository.save(member);
        return new SignUpResponseDto(savedMember.getId(), savedMember.getName(), savedMember.getEmail());
    }

    //로그인
    public LoginResponseDto login(String email, String rawPassword) {
        // 입력받은 userName, password 와 일치하는 Database 조회
        Member member = memberRepository.findMemberByEmailOrElseThrow(email);
        String encodedPassword = passwordEncoder.encode(rawPassword);
        if (passwordEncoder.matches(encodedPassword, member.getPassword())) {
            throw new ApplicationException(ErrorMessageCode.UNAUTHORIZED, "Password Doesn't Match");
        }
        Long index = member.getId();
        return new LoginResponseDto(index);
    }
}
