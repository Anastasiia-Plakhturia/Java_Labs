package org.java.solution.exceptions;

public class InvalidLengthException extends NumberFormatException {

    public InvalidLengthException() {
    }

    public InvalidLengthException(String msg) {
        super(msg);
    }
}
