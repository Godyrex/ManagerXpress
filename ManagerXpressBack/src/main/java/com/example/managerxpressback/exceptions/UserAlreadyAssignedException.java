package com.example.managerxpressback.exceptions;

public class UserAlreadyAssignedException extends RuntimeException{
    public UserAlreadyAssignedException(String message) {
        super(message);
    }
}
