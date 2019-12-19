package slogo.exceptions;

public class FileException extends RuntimeException{
    /**
     * Create an exception based on an issue in our code.
     */
    public FileException(String message, Object ... values) {
        super(String.format(message, values));
    }
}
