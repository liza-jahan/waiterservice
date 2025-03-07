package com.example.waiterservice.exception;


import com.example.waiterservice.model.dto.ErrorDto;
import com.example.waiterservice.model.response.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIResponse<?> handleMethodArgumentException(MethodArgumentNotValidException exception) {
        List<ErrorDto> errors = new ArrayList<>();
        exception.getBindingResult().getFieldErrors()
                .forEach(error -> errors.add(new ErrorDto(error.getField(), error.getDefaultMessage())));

        return APIResponse.builder()
                .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .errors(errors)
                .build();
    }

    @ExceptionHandler(IdentifierExistException.class)
    public APIResponse<?> handleServiceException(IdentifierExistException exception) {
        APIResponse<?> serviceResponse = new APIResponse<>();
        serviceResponse.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
        serviceResponse.setErrors(Collections.singletonList(new ErrorDto(null, exception.getMessage(), exception.getErrorCode())));
        return serviceResponse;
    }

    @ExceptionHandler(NotFoundException.class)
    public APIResponse<?> handleNotFoundException(NotFoundException exception) {
        APIResponse<?> serviceResponse = new APIResponse<>();
        serviceResponse.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
        serviceResponse.setErrors(Collections.singletonList(new ErrorDto(null, exception.getMessage(), exception.getErrorCode())));
        return serviceResponse;
    }

    @ExceptionHandler(TimeExistException.class)
    public APIResponse<?> handleTimeExistException(TimeExistException exception) {
        APIResponse<?> serviceResponse = new APIResponse<>();
        serviceResponse.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
        serviceResponse.setErrors(Collections.singletonList(new ErrorDto(null, exception.getMessage(), exception.getErrorCode())));
        return serviceResponse;
    }
}
