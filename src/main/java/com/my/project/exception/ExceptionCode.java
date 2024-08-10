package com.my.project.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {

    EMAIL_DUPLICATE("M-001", "등록되어 있는 이메일 입니다.");

    private final String code;
    private final String message;

    ExceptionCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
