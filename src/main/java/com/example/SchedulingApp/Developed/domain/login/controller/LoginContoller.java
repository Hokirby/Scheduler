package com.example.SchedulingApp.Developed.domain.login.controller;

import com.example.SchedulingApp.Developed.domain.login.dto.LoginRequestDto;
import com.example.SchedulingApp.Developed.domain.login.dto.LoginResponseDto;
import com.example.SchedulingApp.Developed.domain.member.service.MemberService;
import com.example.SchedulingApp.Developed.exception.ApplicationException;
import com.example.SchedulingApp.Developed.exception.ErrorMessageCode;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LoginContoller {

    private final MemberService memberService;

    @PostMapping("/login")
    public String login(
            @Valid @ModelAttribute LoginRequestDto request,
            HttpServletResponse response // 쿠키값 세팅에 필요
    ) {
        // 로그인 유저 조회
        LoginResponseDto responseDto = memberService.login(request.getEmail(), request.getPassword());
        if (responseDto.getId() == null) {
            // 로그인 실패 예외처리
            throw new ApplicationException(ErrorMessageCode.BAD_REQUEST, "Login Failed");
        }
        // 로그인 성공 처리
        // 쿠키 생성, Value는 문자열로 변환하여야 한다.
        Cookie cookie = new Cookie("memberId", String.valueOf(responseDto.getId()));
        // 쿠키에 값 세팅 (expire 시간을 주지 않으면 세션쿠키가 됨, 브라우저 종료시 로그아웃)
        // Response Set-Cookie: userId=1 형태로 전달된다.
        response.addCookie(cookie);
        // home 페이지로 리다이렉트
        return "redirect:/home";
    }

    @PostMapping("/logout")
    public String logout(
            HttpServletResponse response
    ) {
        Cookie cookie = new Cookie("memberId", null);
        // 0초로 쿠키를 세팅하여 사라지게 만듬
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        // home 페이지로 리다이렉트
        return "redirect:/home";
    }

}
