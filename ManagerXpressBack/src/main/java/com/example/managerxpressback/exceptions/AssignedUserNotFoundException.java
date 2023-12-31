package com.example.managerxpressback.exceptions;

public class AssignedUserNotFoundException extends RuntimeException{
    public AssignedUserNotFoundException(String message) {
        super(message);
    }
}
