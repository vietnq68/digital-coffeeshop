package io.digital.orderservice.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    GENERAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"10000","General Error"),
    ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND,"10001","Entity not found"),
    WRONG_AUTHENTICATION(HttpStatus.BAD_REQUEST,"10002","Username or password is not correct"),
    INVALID_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED,"10003","Invalid access token"),
    ACCESS_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED,"10004","Access token is expired"),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST,"10005","Invalid request"),
    ENTITY_DUPLICATE_EXCEPTION(HttpStatus.BAD_REQUEST,"00006","Duplicated entity")
    ;

    private HttpStatus httpStatus;
    private String errorCode;
    private String errorDescription;

    ErrorCode(HttpStatus status, String errorCode, String description) {
        this.httpStatus = status;
        this.errorCode = errorCode;
        this.errorDescription = description;
    }

    public String code() {
        return this.errorCode;
    }

    public String description() {
        return this.errorDescription;
    }

    public HttpStatus httpStatus() {
        return this.httpStatus;
    }
}
