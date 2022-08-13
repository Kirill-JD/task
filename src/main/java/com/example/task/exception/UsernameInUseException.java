package com.example.task.exception;

public class UsernameInUseException extends Exception{
    public UsernameInUseException(String message) {
        super(message);
    }
}
