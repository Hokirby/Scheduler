package com.example.SchedulingApp.Developed.member.service;

import com.example.SchedulingApp.Developed.login.dto.LoginResponseDto;
import com.example.SchedulingApp.Developed.member.repository.MemberRepository;
import com.example.SchedulingApp.Developed.member.dto.MemberResponseDto;
import com.example.SchedulingApp.Developed.member.dto.SignUpResponseDto;
import com.example.SchedulingApp.Developed.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //회원 정보 저장
    public SignUpResponseDto signUp(String memberName, String password, String email) {
        Member member = new Member(memberName, password, email);
        Member savedMember = memberRepository.save(member);
        return new SignUpResponseDto(savedMember.getId(), savedMember.getName(), savedMember.getEmail());
    }

    //Id로 회원 조회
    public MemberResponseDto findById(Long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if(optionalMember.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
        Member foundMember = optionalMember.get();
        return new MemberResponseDto(foundMember.getId(), foundMember.getName());
    }

    //비밀번호 수정
    @Transactional
    public void updatePassword(Long id, String oldPassword, String newPassword) {
        Member foundMember = memberRepository.findMemberByIdOrElseThrow(id);
        if(!foundMember.getPassword().equals(oldPassword)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password Doesn't Match");
        }
        foundMember.updatePassword(newPassword);
    }

    //로그인
    public LoginResponseDto login(String userName, String password) {
        // 입력받은 userName, password 와 일치하는 Database 조회
        Member member = memberRepository.findMemberByNameOrElseThrow(userName);
        if (!member.getPassword().equals(password)){
            throw new RuntimeException();
        }
        Long index = member.getId();
        return new LoginResponseDto(index);
    }
}
