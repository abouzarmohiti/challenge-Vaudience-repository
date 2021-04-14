package com.Vaudience.challenge.exception;

public class VersionNotSupportedException extends Exception {

    private static final long serialVersionUID = 1L;

    public VersionNotSupportedException(String exception) {
        super(exception);
    }
}
