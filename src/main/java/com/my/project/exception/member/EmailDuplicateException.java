package com.my.project.exception.member;

import com.my.project.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class EmailDuplicateException extends DuplicateException {

    private String value;

    public EmailDuplicateException(final String email) {
        super(email, ExceptionCode.EMAIL_DUPLICATE);
    }

}
