package com.example.SchedulingApp.Developed.domain.member.service;

import com.example.SchedulingApp.Developed.domain.login.dto.LoginResponseDto;
import com.example.SchedulingApp.Developed.domain.member.dto.MemberResponseDto;
import com.example.SchedulingApp.Developed.domain.member.dto.SignUpResponseDto;
import com.example.SchedulingApp.Developed.domain.member.entity.Member;
import com.example.SchedulingApp.Developed.domain.member.repository.MemberRepository;
import com.example.SchedulingApp.Developed.exception.ApplicationException;
import com.example.SchedulingApp.Developed.exception.ErrorMessageCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        if (optionalMember.isEmpty()) {
            throw new ApplicationException(ErrorMessageCode.NOT_FOUND, "Does Not Exist Id = " + id);
        }
        Member foundMember = optionalMember.get();
        return new MemberResponseDto(foundMember.getId(), foundMember.getName());
    }

    //비밀번호 수정
    @Transactional
    public void updatePassword(Long id, String oldPassword, String newPassword) {
        Member foundMember = memberRepository.findMemberByIdOrElseThrow(id);
        if (!foundMember.getPassword().equals(oldPassword)) {
            throw new ApplicationException(ErrorMessageCode.UNAUTHORIZED, "Password Doesn't Match");
        }
        foundMember.updatePassword(newPassword);
    }

    //회원정보 삭제
    public void delete(Long id, String password) {
        Member foundMember = memberRepository.findMemberByIdOrElseThrow(id);
        if (!foundMember.getPassword().equals(password)) {
            throw new ApplicationException(ErrorMessageCode.UNAUTHORIZED, "Password Doesn't Match");
        }
        memberRepository.delete(foundMember);
    }

    //로그인
    public LoginResponseDto login(String email, String password) {
        // 입력받은 userName, password 와 일치하는 Database 조회
        Member member = memberRepository.findMemberByEmailOrElseThrow(email);
        if (!member.getPassword().equals(password)) {
            throw new ApplicationException(ErrorMessageCode.UNAUTHORIZED, "Password Doesn't Match");
        }
        Long index = member.getId();
        return new LoginResponseDto(index);
    }

}
