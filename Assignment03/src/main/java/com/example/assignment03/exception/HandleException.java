package com.example.assignment03.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HandleException  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = BasicException.class)
    public ResponseEntity<?> handle(BasicException basicException){
        return new ResponseEntity<>(basicException.getMessage(), basicException.getCode().equals("NOT_FOUND") ? HttpStatus.NOT_FOUND :  HttpStatus.BAD_REQUEST);
    }
}
