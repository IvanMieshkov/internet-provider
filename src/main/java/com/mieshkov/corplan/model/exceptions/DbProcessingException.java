package com.mieshkov.corplan.model.exceptions;

public class DbProcessingException extends RuntimeException {
    public DbProcessingException(String message) {
        super(message);
    }
}
