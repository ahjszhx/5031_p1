package hangman.exception;

public class NonAlphabeticInputException extends IllegalArgumentException {

    public NonAlphabeticInputException() {
        super("Input must be a single alphabetic character.");
    }

    public NonAlphabeticInputException(String message) {
        super(message);
    }
}
