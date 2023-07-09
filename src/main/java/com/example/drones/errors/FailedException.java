package com.example.drones.errors;

public class FailedException extends RuntimeException {
    public FailedException(String message) {
        super(message);
    }
}
