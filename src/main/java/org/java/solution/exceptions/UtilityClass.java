package org.java.solution.exceptions;


public final class UtilityClass {

    public static void checkContentExistence(String row) {
        if (row.length() <= 1) {
            throw new EmptyFileException();
        }
    }

    public static void checkEmailExistence(String email) {
        if (email.isEmpty()) {
            throw new EmptyRowException();
        }
    }

    public static void checkLength(int length) {
        if (length < 1) {
            throw new InvalidLengthException();
        }
    }
}
