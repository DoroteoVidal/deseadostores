package com.app.deseadostores.exception;

public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super("User not found or already inactive");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
