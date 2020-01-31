package io.digital.apigateway.exception;

public class InvalidTokenException extends BaseException {
    public InvalidTokenException() {
        super(ErrorCode.INVALID_ACCESS_TOKEN);
    }

    public InvalidTokenException(String message) {
        super(ErrorCode.INVALID_ACCESS_TOKEN,message);
    }
}
