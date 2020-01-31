package io.digital.orderservice.controller;

import io.digital.orderservice.exception.ErrorCode;

public class ErrorResponse {
    private String error;
    private String errorDescription;

    public ErrorResponse() {
        super();
        this.error = ErrorCode.GENERAL_ERROR.code();
        this.errorDescription = ErrorCode.GENERAL_ERROR.description();
    }

    public ErrorResponse(String errCode, String errorDescription) {
        this.error = errCode;
        this.errorDescription = errorDescription;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
