package com.example.assignment03.exception;

import lombok.Data;

@Data
public class BasicException extends RuntimeException{

    public static  final  BasicException NOT_FOUND = new BasicException("NOT_FOUND");
    public static  final  BasicException INVALID_ARGUMENT = new BasicException("INVALID_ARGUMENT");

    private String code;
    private String message;

    public BasicException(String code){
        this.code = code;
    }
    public BasicException(String message, String code){
        this.message = message;
        this.code = code;
    }

    public BasicException addError(String message){
        return new BasicException(message, this.code);
    }
}
