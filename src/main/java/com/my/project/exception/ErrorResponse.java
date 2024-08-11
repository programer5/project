package com.my.project.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

    private int status;
    private String code;
    private String message;
    private List<String> values = new ArrayList<>();

    public ErrorResponse(int status, final ExceptionCode exceptionCode) {
        this.status = status;
        this.message = exceptionCode.getMessage();
        this.code = exceptionCode.getCode();
    }

    public ErrorResponse(int status, ExceptionCode code, String value) {
        this(status, code);
        this.values = List.of(value);
    }

    public ErrorResponse(int status, ExceptionCode code, List<String> values) {
        this(status, code);
        this.values = values;
    }

    public static ErrorResponse of(int status, ExceptionCode code) {
        return new ErrorResponse(status, code);
    }

    public static ErrorResponse of(int status, ExceptionCode code, String value) {
        return new ErrorResponse(status, code, value);
    }

    public static ErrorResponse of(final int status, final ExceptionCode exceptionCode,
                                   final BindingResult bindingResult) {

        List<String> values = bindingResult.getFieldErrors().stream()
                .map(error ->
                        error.getRejectedValue() == null ? "" : error.getRejectedValue().toString())
                .collect(Collectors.toList());
        return new ErrorResponse(status, exceptionCode, values);
    }
}
