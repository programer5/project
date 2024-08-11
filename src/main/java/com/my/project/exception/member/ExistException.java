package com.my.project.exception.member;

import com.my.project.exception.BaseException;
import com.my.project.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class ExistException extends BaseException {

    private String value;

    public ExistException(String value) {
        this(value, ExceptionCode.NON_EXIST_MEMBER);
        this.value = value;
    }

    public ExistException(String value, ExceptionCode exceptionCode) {
        super(value, exceptionCode);
        this.value = value;
    }

}
