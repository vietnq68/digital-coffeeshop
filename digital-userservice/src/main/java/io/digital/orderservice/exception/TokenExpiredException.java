package io.digital.orderservice.exception;

public class TokenExpiredException extends BaseException {
    public TokenExpiredException() {
        super(ErrorCode.ACCESS_TOKEN_EXPIRED);
    }

    public TokenExpiredException(String message) {
        super(ErrorCode.ACCESS_TOKEN_EXPIRED,message);
    }
}
