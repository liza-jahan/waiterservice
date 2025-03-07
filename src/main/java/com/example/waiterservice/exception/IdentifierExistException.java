package com.example.waiterservice.exception;


import com.example.waiterservice.utils.ErrorDetails;

public class IdentifierExistException  extends BaseException{
    public IdentifierExistException(String message, Throwable ex, String errorCode) {
        super(message, ex, errorCode);
    }

    public IdentifierExistException(ErrorDetails errors) {

        super(errors.getMessage(), errors.getCode());
    }
}
