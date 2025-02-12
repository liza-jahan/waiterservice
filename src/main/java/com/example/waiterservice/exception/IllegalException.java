package com.example.waiterservice.exception;

public class IllegalException extends BaseException{

    public IllegalException(String message, String errorCode) {
        super(message, errorCode);
    }
}
