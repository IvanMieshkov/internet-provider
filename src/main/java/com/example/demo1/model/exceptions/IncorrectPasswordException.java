package com.example.demo1.model.exceptions;

/**
 * @author Ivan Mieshkov
 */
public class IncorrectPasswordException extends Exception {
    public IncorrectPasswordException(String message) {
        super(message);
    }
}
