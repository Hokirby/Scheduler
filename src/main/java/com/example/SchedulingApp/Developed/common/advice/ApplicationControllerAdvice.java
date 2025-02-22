package com.example.SchedulingApp.Developed.common.advice;

import com.example.SchedulingApp.Developed.exception.ApplicationException;
import com.example.SchedulingApp.Developed.exception.ErrorResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(ApplicationException.class)
    public ErrorResultDto exceptionHandle(ApplicationException e, String logMessage) {
        log.error(logMessage);
        return new ErrorResultDto(e.getErrorMessageCode(), logMessage);
    }
}
