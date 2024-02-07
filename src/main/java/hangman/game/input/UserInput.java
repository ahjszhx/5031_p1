package hangman.game.input;

/**
 * The UserInput interface defines a contract for input mechanisms in the hangman game or any other application
 * requiring user input. It abstracts the method of input collection, allowing different implementations to provide
 * input through various means (e.g., console input, GUI input fields, etc.). This flexibility facilitates easy
 * adaptation and integration of different input methods without altering the core logic that depends on user input.
 */
public interface UserInput {

    /**
     * Reads the next line of input from the user.
     * This method is designed to be implemented by different classes that handle user input, providing a standardized
     * way to capture a line of text. Implementations could vary from reading console input to capturing input from a GUI component.
     *
     * @return A String representing the user's input.
     */
    String nextLine();

}
