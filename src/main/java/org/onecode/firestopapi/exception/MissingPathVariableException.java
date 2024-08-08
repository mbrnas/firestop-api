package org.onecode.firestopapi.exception;

public class MissingPathVariableException extends RuntimeException {
    public MissingPathVariableException(String message) {
        super(message);
    }
}
