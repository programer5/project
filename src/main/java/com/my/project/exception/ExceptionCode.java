package com.my.project.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {

    INVALID_INPUT_VALUE("C-001", "유효하지 않는 입력값 입니다."),
    INTERNAL_SERVER_ERROR("C-004", "서버에러"),

    EMAIL_DUPLICATE("M-001", "등록되어 있는 이메일 입니다."),
    NON_EXIST_MEMBER("M-002", "존재하지 않는 멤버입니다.");

    private final String code;
    private final String message;

    ExceptionCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
