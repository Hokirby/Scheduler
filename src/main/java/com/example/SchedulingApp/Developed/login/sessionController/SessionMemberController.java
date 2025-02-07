package com.example.SchedulingApp.Developed.login.sessionController;

import com.example.SchedulingApp.Developed.config.Const;
import com.example.SchedulingApp.Developed.login.dto.LoginRequestDto;
import com.example.SchedulingApp.Developed.login.dto.LoginResponseDto;
import com.example.SchedulingApp.Developed.member.dto.MemberResponseDto;
import com.example.SchedulingApp.Developed.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SessionMemberController {

    private final MemberService memberService;

    @PostMapping("/session-login")
    public String login(
            @Valid @ModelAttribute LoginRequestDto dto,
            HttpServletRequest request
    ) {
        LoginResponseDto responseDto = memberService.login(dto.getMemberName(), dto.getPassword());
        Long memberId = responseDto.getId();
        // 실패시 예외처리
        if (memberId == null) {
            return "session-login";
        }
        // 로그인 성공시 로직
        // Session의 Default Value는 true이다.
        // Session이 request에 존재하면 기존의 Session을 반환하고,
        // Session이 request에 없을 경우에 새로 Session을 생성한다.
        HttpSession session = request.getSession();
        // 회원 정보 조회
        MemberResponseDto loginMember = memberService.findById(memberId);
        // Session에 로그인 회원 정보를 저장한다.
        session.setAttribute(Const.LOGIN_MEMBER, loginMember);
        // 로그인 성공시 리다이렉트
        return "redirect:/session-home";
    }

    @PostMapping("/session-logout")
    public String logout(HttpServletRequest request) {
        // 로그인하지 않으면 HttpSession이 null로 반환된다.
        HttpSession session = request.getSession(false);
        // 세션이 존재하면 -> 로그인이 된 경우
        if(session != null) {
            session.invalidate(); // 해당 세션(데이터)을 삭제한다.
        }
        return "redirect:/session-home";
    }
}