package com.example.SchedulingApp.Developed.common.fiter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class LogFilter implements Filter {
    @Override
    public void doFilter(
            ServletRequest request, ServletResponse response, FilterChain chain
    ) throws IOException, ServletException {
        //Filter 에서 수행할 Logic
        //Servlet Request 는 기능이 별로 없어서 기능이 많은 HttpServletRequest 로 다운 캐스팅
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        log.info("request URI={}", requestURI);
        //chain 이 없으면 Servlet 을 바로 호출
        chain.doFilter(request, response);
    }
}
