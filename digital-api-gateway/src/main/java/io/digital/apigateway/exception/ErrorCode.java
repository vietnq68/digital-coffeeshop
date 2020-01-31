package io.digital.apigateway.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    GENERAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"00000","General Error"),
    INVALID_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED,"00003","Invalid access token"),
    ACCESS_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED,"00004","Access token is expired"),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST,"00005","Invalid request"),
    ACCESS_DENIED(HttpStatus.FORBIDDEN,"00006","Access denied");
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
