package com.mediflix.backend.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public enum ErrorCode {

    INTERNAL_SERVER_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "서버 내부에서 문제가 발생했습니다."),
    NOT_FOUND(false, HttpStatus.NOT_FOUND.value(), "해당 로그인 정보는 존재하지 않습니다."),
    UNAUTHORIZED(false, HttpStatus.UNAUTHORIZED.value(), "권한이 없습니다."),
    // Success
    SUCCESS(true, HttpStatus.OK.value(), "요청에 성공하였습니다.");

    private Boolean isSuccess;
    private int code;
    private String message;

    ErrorCode(Boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}