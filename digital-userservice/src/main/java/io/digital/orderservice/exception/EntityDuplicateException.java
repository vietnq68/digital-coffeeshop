package io.digital.orderservice.exception;

public class EntityDuplicateException extends BaseException {
    public EntityDuplicateException() {
        super(ErrorCode.ENTITY_DUPLICATE_EXCEPTION);
    }

    public EntityDuplicateException(String message) {
        super(ErrorCode.ENTITY_DUPLICATE_EXCEPTION, message);
    }
}
