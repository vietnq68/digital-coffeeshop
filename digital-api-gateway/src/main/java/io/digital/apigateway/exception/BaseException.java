package io.digital.apigateway.exception;

import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException{
    private String errorCode;
    private String errorDescription;
    private HttpStatus httpStatus;

    public BaseException() {
        super();
        this.errorCode = ErrorCode.GENERAL_ERROR.code();
        this.errorDescription = ErrorCode.GENERAL_ERROR.description();
        this.httpStatus = ErrorCode.GENERAL_ERROR.httpStatus();
    }

    public BaseException(String message) {
        super(message);
        this.errorCode = ErrorCode.GENERAL_ERROR.code();
        this.errorDescription = ErrorCode.GENERAL_ERROR.description();
        this.httpStatus = ErrorCode.GENERAL_ERROR.httpStatus();
    }


    public BaseException(ErrorCode errorCode) {
        this.errorCode = errorCode.code();
        this.errorDescription = errorCode.description();
        this.httpStatus = errorCode.httpStatus();
    }

    public BaseException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode.code();
        this.errorDescription = errorCode.description();
        this.httpStatus = errorCode.httpStatus();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
