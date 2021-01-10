package ua.com.exceptions;

public class NoSuchDepartmentException extends Exception {
    public NoSuchDepartmentException() {
    }

    public NoSuchDepartmentException(String message) {
        super(message);
    }

    public NoSuchDepartmentException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchDepartmentException(Throwable cause) {
        super(cause);
    }
}
