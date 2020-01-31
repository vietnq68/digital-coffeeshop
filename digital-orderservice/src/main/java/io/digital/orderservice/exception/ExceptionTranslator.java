package io.digital.orderservice.exception;

import io.digital.orderservice.controller.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionTranslator {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionTranslator.class);
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(HttpServletRequest request, Exception e) {
        logger.error("Exception occurs: ",e);
        ErrorResponse errorResponse = new ErrorResponse();
        ResponseEntity responseEntity;
        if(e instanceof BaseException) {
            BaseException baseException = (BaseException) e;
            errorResponse.setError(baseException.getErrorCode());
            errorResponse.setErrorDescription(baseException.getErrorDescription());
            responseEntity = new ResponseEntity(errorResponse,((BaseException) e).getHttpStatus());
        } else {
            responseEntity = new ResponseEntity(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
