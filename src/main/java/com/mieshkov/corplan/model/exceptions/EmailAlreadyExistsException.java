package com.mieshkov.corplan.model.exceptions;

/**
 * @author Ivan Mieshkov
 */
public class EmailAlreadyExistsException extends Exception {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}