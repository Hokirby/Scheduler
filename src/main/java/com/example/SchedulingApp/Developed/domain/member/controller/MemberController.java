package com.example.SchedulingApp.Developed.domain.member.controller;

import com.example.SchedulingApp.Developed.domain.member.dto.MemberResponseDto;
import com.example.SchedulingApp.Developed.domain.member.dto.UpdatePasswordRequestDto;
import com.example.SchedulingApp.Developed.domain.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
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

    //id 로 회원조회
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> findById(@PathVariable Long id, HttpServletRequest request) {
        MemberResponseDto memberResponseDto = memberService.findById(request, id);
        return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
    }

    //id로 비밀번호 수정
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, HttpServletRequest request, @Valid @RequestBody UpdatePasswordRequestDto requestDto) {
        memberService.updatePassword(request, id, requestDto.getOldPassword(), requestDto.getNewPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //id와 비밀번호로 회원 정보 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, HttpServletRequest request, @RequestParam String password) {
        memberService.delete(request, id, password);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
