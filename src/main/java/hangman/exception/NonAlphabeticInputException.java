package hangman.exception;

/**
 * The NonAlphabeticInputException class is a custom exception that extends IllegalArgumentException.
 * It is specifically designed to be thrown when an input provided to the hangman game does not meet
 * the expected criteria of being a single alphabetic character. This exception helps in clearly
 * indicating the cause of the error and enforcing input validation within the game.
 */
public class NonAlphabeticInputException extends IllegalArgumentException {

    /**
     * Default constructor that uses a predefined error message to indicate that the input
     * must be a single alphabetic character. This constructor can be used when a generic
     * error message suffices.
     */
    public NonAlphabeticInputException() {
        super("Input must be a single alphabetic character.");
    }

    /**
     * Constructor with a custom message. This allows for specifying more detailed or context-specific
     * error messages when throwing the exception, providing flexibility in how input validation errors
     * are communicated.
     *
     * @param message The custom message that explains the specific reason for the exception.
     */
    public NonAlphabeticInputException(String message) {
        super(message);
    }
}
