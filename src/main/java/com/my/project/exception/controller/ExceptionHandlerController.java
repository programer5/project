package com.my.project.exception.controller;

import com.my.project.exception.BaseException;
import com.my.project.exception.ErrorResponse;
import com.my.project.exception.ExceptionCode;
import com.my.project.exception.member.DuplicateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.info("Handle MethodArgumentNotValidException", e);

        int status = HttpStatus.BAD_REQUEST.value();

        ErrorResponse response = ErrorResponse.of(status, ExceptionCode.INVALID_INPUT_VALUE, e.getBindingResult());

        return new ResponseEntity<>(response, HttpStatus.valueOf(status));
    }

    @ExceptionHandler(DuplicateException.class)
    protected ResponseEntity<ErrorResponse> handleDuplicationException(final DuplicateException e) {
        log.info("Handle DuplicationException", e);

        final ExceptionCode errorCode = e.getExceptionCode();
        final String value = e.getValue();
        final int status = HttpStatus.CONFLICT.value();

        final ErrorResponse errorResponse = ErrorResponse.of(status, errorCode, value);

        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(status));
    }

    @ExceptionHandler(BaseException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(final BaseException e) {
        log.info("Handle BusinessException", e);

        final ExceptionCode errorCode = e.getExceptionCode();
        final int status = HttpStatus.BAD_GATEWAY.value();

        final ErrorResponse errorResponse = ErrorResponse.of(status, errorCode);

        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(status));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("Handle Exception", e);

        final int status = HttpStatus.INTERNAL_SERVER_ERROR.value();

        final ErrorResponse response = ErrorResponse.of(status, ExceptionCode.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
