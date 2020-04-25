package org.java.solution.exceptions;

public class EmptyRowException extends RuntimeException {

    public EmptyRowException() {
    }

    public EmptyRowException(String message) {
        super(message);
    }
}
