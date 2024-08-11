package com.my.project.exception.member;

import com.my.project.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class NonExistMemberException extends DuplicateException {

    private String value;

    public NonExistMemberException(final String email) {
        super(email, ExceptionCode.NON_EXIST_MEMBER);
    }

}
