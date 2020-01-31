package io.digital.apigateway.exception;

public class BadRequestException extends BaseException {
    public BadRequestException() {
        super(ErrorCode.INVALID_REQUEST);
    }

    public BadRequestException(String message) {
        super(ErrorCode.INVALID_REQUEST,message);
    }
}
