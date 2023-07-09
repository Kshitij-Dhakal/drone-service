package com.example.drones.dtos;

public class ErrorResponseDto<T> {
    private final String message;
    private final T details;

    public ErrorResponseDto(String message, T details) {
        this.message = message;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public T getDetails() {
        return details;
    }
}
