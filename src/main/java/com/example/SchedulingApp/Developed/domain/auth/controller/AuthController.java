package com.example.SchedulingApp.Developed.domain.auth.controller;

import com.example.SchedulingApp.Developed.common.consts.Const;
import com.example.SchedulingApp.Developed.domain.auth.dto.LoginRequestDto;
import com.example.SchedulingApp.Developed.domain.auth.dto.LoginResponseDto;
import com.example.SchedulingApp.Developed.domain.auth.service.AuthService;
import com.example.SchedulingApp.Developed.domain.member.dto.MemberResponseDto;
import com.example.SchedulingApp.Developed.domain.member.dto.SignUpResponseDto;
import com.example.SchedulingApp.Developed.domain.member.dto.SignupRequestDto;
import com.example.SchedulingApp.Developed.domain.member.service.MemberService;
import com.example.SchedulingApp.Developed.exception.ApplicationException;
import com.example.SchedulingApp.Developed.exception.ErrorMessageCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<Void> login(
            @Valid @ModelAttribute LoginRequestDto dto,
            HttpServletRequest request
    ) {
        //로그인이 이미 되어있는 경우 로그아웃
        if (request.getSession(false) != null) {
            request.getSession(false).invalidate();
            return new ResponseEntity<>(HttpStatus.OK);
        }
        LoginResponseDto responseDto = authService.login(dto.getEmail(), dto.getPassword());
        Long memberId = responseDto.getId();
        // 실패시 예외처리
        if (memberId == null) {
            throw new ApplicationException(ErrorMessageCode.BAD_REQUEST, "Check The Mandatory Entry(email)");
        }
        // 로그인 성공시 로직
        // Session의 Default Value는 true이다.
        // Session이 request에 존재하면 기존의 Session을 반환하고,
        // Session이 request에 없을 경우에 새로 Session을 생성한다.
        HttpSession session = request.getSession();
        // 회원 정보 조회
        MemberResponseDto loginMember = memberService.findById(request, memberId);
        // Session에 로그인 회원 정보를 저장한다.
        session.setAttribute(Const.LOGIN_MEMBER, loginMember);
        // 로그인 성공시 리다이렉트
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        // 로그인하지 않으면 HttpSession이 null로 반환된다.
        HttpSession session = request.getSession(false);
        // 세션이 존재하면 -> 로그인이 된 경우
        if(session != null) {
            session.invalidate(); // 해당 세션(데이터)을 삭제한다.
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@Valid @RequestBody SignupRequestDto requestDto) {
        SignUpResponseDto signUpResponseDto = authService.signUp(
                requestDto.getMemberName(),
                requestDto.getPassword(),
                requestDto.getEmail()
        );
        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
    }
}
