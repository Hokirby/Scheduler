package com.example.SchedulingApp.Developed.login.sessionController;

import com.example.SchedulingApp.Developed.config.Const;
import com.example.SchedulingApp.Developed.member.dto.MemberResponseDto;
import com.example.SchedulingApp.Developed.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
public class SessionHomeController {

    private final MemberService memberService;

    @GetMapping("/session-home")
    public String home(
            HttpServletRequest request,
            Model model
    ) {
        // default인 true로 설정되면 로그인하지 않은 사람들도 값은 비어있지만 세션이 만들어진다.
        // session을 생성할 의도가 없다.
        HttpSession session = request.getSession(false);
        // session이 없으면 로그인 페이지로 이동
        if(session == null) {
            return "session-login";
        }
        // session에 저장된 유저정보 조회
        // 반환타입이 Object여서 Type Casting이 필요하다.
        MemberResponseDto loginMember = (MemberResponseDto) session.getAttribute(Const.LOGIN_MEMBER);

        // Session에 유저 정보가 없으면 login 페이지 이동
        if (loginMember == null) {
            return "session-login";
        }

        // Session이 정상적으로 조회되면 로그인된것으로 간주
        model.addAttribute("loginMember", loginMember);
        // home 화면으로 이동
        return "session-home";
    }

    @GetMapping("/v2/session-home")
    public String homeV2(
            // Session 이 필수값은 아니다. 로그인 했거나 안했거나 판별해야하니 required false
            @SessionAttribute(name = Const.LOGIN_MEMBER, required = false) MemberResponseDto loginMember,
            Model model
    ) {
        // session 에 loginUser 가 없으면 Login 페이지로 이동
        if (loginMember == null) {
            return "session-login";
        }
        // Session 이 정상적으로 조회되면 로그인된것으로 간주
        model.addAttribute("loginMember", loginMember);
        // home 화면으로 이동
        return "session-home";
    }
}