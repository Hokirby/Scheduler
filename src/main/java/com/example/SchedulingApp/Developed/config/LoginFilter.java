package com.example.SchedulingApp.Developed.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;


@Slf4j
public class LoginFilter implements Filter {

    //인증 안하는 URL
    private static final String[] WHITE_LIST = {"/", "/user/signup", "/login", "/logout"};

    // 로그인 여부를 확인하는 URL 인지 체크
    private boolean isWhiteList(String requestURI) {
        // request URI 가 whiteListURL 에 포함되는지 확인
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //다양한 기능을 사용하기 위해 다운 캐스팅
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        //다양한 기능을 사용하기 위해 다운 캐스팅
        log.info("Run Login Filter Logic");
        if (!isWhiteList(requestURI)) {
            HttpSession session = httpRequest.getSession(false);
            if (session == null || session.getAttribute("member") == null) {
                throw new RuntimeException();
            }
            //로그인 성공 로직
            log.info("Logged In Successfully");
        }
        chain.doFilter(request, response);
    }
}
