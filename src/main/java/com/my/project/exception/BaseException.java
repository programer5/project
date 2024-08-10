package com.my.project.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    private final ExceptionCode exceptionCode;

    public BaseException(String message, ExceptionCode exceptionCode) {
        super(message);
        this.exceptionCode = exceptionCode;
    }
}
