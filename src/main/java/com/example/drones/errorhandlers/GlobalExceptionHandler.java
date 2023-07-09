package com.example.drones.errorhandlers;

import com.example.drones.dtos.ErrorResponseDto;
import com.example.drones.errors.InvalidStateException;
import com.example.drones.errors.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponseDto<String> handleNotFoundException(NotFoundException notFoundException) {
        return new ErrorResponseDto<>("Not Found", notFoundException.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidStateException.class)
    public ErrorResponseDto<String> handleNotFoundException(InvalidStateException invalidStateException) {
        return new ErrorResponseDto<>("Invalid State", invalidStateException.getMessage());
    }
}
