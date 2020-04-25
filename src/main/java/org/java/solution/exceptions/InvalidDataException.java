package org.java.solution.exceptions;

public class InvalidDataException extends RuntimeException {

    public InvalidDataException() {
    }

    public InvalidDataException(String message) {
        super(message);
    }
}
