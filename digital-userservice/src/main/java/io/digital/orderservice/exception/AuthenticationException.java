package io.digital.orderservice.exception;

public class AuthenticationException extends BaseException {
    public AuthenticationException() {
        super(ErrorCode.WRONG_AUTHENTICATION);
    }

    public AuthenticationException(String message) {
        super(ErrorCode.WRONG_AUTHENTICATION,message);
    }
}
