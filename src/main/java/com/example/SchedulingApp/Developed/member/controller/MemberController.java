package com.example.SchedulingApp.Developed.member.controller;

import com.example.SchedulingApp.Developed.member.dto.MemberResponseDto;
import com.example.SchedulingApp.Developed.member.dto.SignUpResponseDto;
import com.example.SchedulingApp.Developed.member.dto.SignupRequestDto;
import com.example.SchedulingApp.Developed.member.dto.UpdatePasswordRequestDto;
import com.example.SchedulingApp.Developed.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@Valid @RequestBody SignupRequestDto requestDto) {
        SignUpResponseDto signUpResponseDto = memberService.signUp(
                requestDto.getMemberName(),
                requestDto.getPassword(),
                requestDto.getEmail()
        );
        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
    }

    //id 로 회원조회
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> findById(@PathVariable Long id) {
        MemberResponseDto memberResponseDto = memberService.findById(id);
        return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
    }

    //id로 비밀번호 수정
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody UpdatePasswordRequestDto requestDto) {
        memberService.updatePassword(id, requestDto.getOldPassword(), requestDto.getNewPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
