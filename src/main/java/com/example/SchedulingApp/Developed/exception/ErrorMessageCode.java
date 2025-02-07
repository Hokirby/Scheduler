package com.example.SchedulingApp.Developed.exception;

import lombok.Getter;

@Getter
public enum ErrorMessageCode {
    BAD_REQUEST("400", "Bad Request"),
    UNAUTHORIZED("401", "Unauthorized"),
    NOT_FOUND("404", "Not Found");

    private final String code;
    private final String message;

    ErrorMessageCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}