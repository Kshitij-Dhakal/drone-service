package com.example.drones.errorhandlers;

import com.example.drones.dtos.ErrorResponseDto;
import com.example.drones.errors.FailedException;
import com.example.drones.errors.NotFoundException;
import com.example.drones.errors.ValidationException;
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
    @ExceptionHandler(FailedException.class)
    public ErrorResponseDto<String> handleFailedException(FailedException failedException) {
        return new ErrorResponseDto<>("Failed", failedException.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ErrorResponseDto<String> handleValidationException(ValidationException validationException) {
        return new ErrorResponseDto<>("Validation Failed", validationException.getMessage());
    }
}
