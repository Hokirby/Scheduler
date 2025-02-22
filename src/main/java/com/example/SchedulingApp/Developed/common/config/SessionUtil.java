package com.example.SchedulingApp.Developed.common.config;

import com.example.SchedulingApp.Developed.exception.ApplicationException;
import com.example.SchedulingApp.Developed.exception.ErrorMessageCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

@Component
public class SessionUtil {
    public Long findUserIdBySession(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        Long userId = null;
        if (session != null && session.getAttribute("userId") != null) {
            userId = (Long) session.getAttribute("userId");
        }
        if (userId == null) {
            throw new ApplicationException(ErrorMessageCode.UNAUTHORIZED, "Found No Member Logged In");
        }
        return userId;
    }
}

