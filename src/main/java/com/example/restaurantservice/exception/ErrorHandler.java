package com.example.restaurantservice.exception;

import com.example.restaurantservice.exception.ErrorResponse;
import com.example.restaurantservice.exception.not_found.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    //400
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleHttpMessageNotReadableException(
            final HttpMessageNotReadableException e
    ) {
        log.warn("ERROR: {}, message: {}", e.getClass().getName(), e.getMessage());
        return new ErrorResponse(
                e.getMessage()
        );
    }

    //404
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorResponse handleNotFoundException(
            final NotFoundException e
    ) {
        log.warn("ERROR: {}, message: {}", e.getClass().getName(), e.getMessage());
        return new ErrorResponse(
                e.getMessage()
        );
    }


    // 500
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ErrorResponse handleRuntimeException(
            final RuntimeException e
    ) {
        log.error("ERROR: {}, message: {}", e.getClass().getName(), e.getMessage(), e);
        return new ErrorResponse(
                e.getMessage()
        );
    }

}