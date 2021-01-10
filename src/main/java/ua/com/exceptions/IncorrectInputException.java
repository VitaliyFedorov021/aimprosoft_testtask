package ua.com.exceptions;

public class IncorrectInputException extends Exception {
    public IncorrectInputException() {
    }

    public IncorrectInputException(String message) {
        super(message);
    }

    public IncorrectInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectInputException(Throwable cause) {
        super(cause);
    }
}
