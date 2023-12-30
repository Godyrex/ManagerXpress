package com.example.managerxpressback.exceptions;

public class TableNotFoundException extends RuntimeException{
    public TableNotFoundException(String message) {
        super(message);
    }
}
