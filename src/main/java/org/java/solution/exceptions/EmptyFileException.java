package org.java.solution.exceptions;

public class EmptyFileException extends RuntimeException {

    public EmptyFileException() {
    }

    public EmptyFileException(String message) {
        super(message);
    }
}
