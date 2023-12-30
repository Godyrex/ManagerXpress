package com.example.managerxpressback.security.services;

public class RoleAlreadyAssignedException extends RuntimeException {
    public RoleAlreadyAssignedException(String message) {
        super(message);
    }
}

