package com.example.waiterservice.exception;

public class TokenExpiredException extends RuntimeException{
    public TokenExpiredException(String message, Throwable cause) {
        super(message, cause);
    }
}

