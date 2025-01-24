package com.rama.app.exception;

public class PasswordCheck extends RuntimeException {
    public PasswordCheck(String message) {
        super(message);
    }
}
