package io.digital.apigateway.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.digital.apigateway.exception.ErrorCode;

public class ErrorResponse {
    private ObjectMapper objectMapper = new ObjectMapper();
    private String error;
    private String errorDescription;

    public ErrorResponse() {
        super();
        this.error = ErrorCode.GENERAL_ERROR.code();
        this.errorDescription = ErrorCode.GENERAL_ERROR.description();
    }

    public ErrorResponse(ErrorCode errorCode) {
        this.error = errorCode.code();
        this.errorDescription = errorCode.description();
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

    @Override
    public String toString() {
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return super.toString();
        }
    }
}
