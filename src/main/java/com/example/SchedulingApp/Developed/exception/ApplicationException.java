package com.example.SchedulingApp.Developed.exception;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
public class ApplicationException extends RuntimeException {
  private final ErrorMessageCode errorMessageCode;
  private final String logMessage;

  public ApplicationException(ErrorMessageCode errorMessageCode, String logMessage) {
    super(errorMessageCode.getMessage());
    this.errorMessageCode = errorMessageCode;
    this.logMessage = logMessage;
  }
}
