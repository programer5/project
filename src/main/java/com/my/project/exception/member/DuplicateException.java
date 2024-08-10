package com.my.project.exception.member;

import com.my.project.exception.BaseException;
import com.my.project.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class DuplicateException extends BaseException {

    private String value;

    public DuplicateException(String value) {
        this(value, ExceptionCode.EMAIL_DUPLICATE);
        this.value = value;
    }

    public DuplicateException(String value, ExceptionCode exceptionCode) {
        super(value, exceptionCode);
        this.value = value;
    }

}
