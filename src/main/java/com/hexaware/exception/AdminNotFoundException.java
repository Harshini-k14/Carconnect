package com.hexaware.exception;

public class AdminNotFoundException extends Exception {
    public AdminNotFoundException(String message) {
        super(message);
    }

    public AdminNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

