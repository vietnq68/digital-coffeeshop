package io.digital.orderservice.exception;

public class ExceedQueueException extends BaseException {
    public ExceedQueueException(){
        super(ErrorCode.QUEUE_SIZE_EXCEEDED);
    }

    public ExceedQueueException(String message){
        super(ErrorCode.QUEUE_SIZE_EXCEEDED,message);
    }
}
