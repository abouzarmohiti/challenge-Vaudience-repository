package com.Vaudience.challenge.exception;

public class ContactIsExistException extends Exception {
    private static final long serialVersionUID = 2L;

    public ContactIsExistException(String exception) {
        super(exception);
    }
}
