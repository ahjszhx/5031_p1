package hangman.game.input;

import java.util.Scanner;

/**
 * The ScannerInput class implements the UserInput interface, providing a concrete method to read input from the console.
 * This class utilizes the standard Java Scanner class to capture user input, making it suitable for console-based applications
 * like a command-line hangman game. It is designed to be easily integrated with any system requiring user input from the standard input stream.
 */
public class ScannerInput implements UserInput{

    /**
     * Reads the next line of input from the console.
     * This method waits for the user to enter a line of text and press Enter. It then returns the entered text,
     * allowing it to be processed by the game logic or any other system requiring user input.
     *
     * @return The line of text entered by the user.
     */
    @Override
    public String nextLine() {
        // Create a Scanner object attached to the System.in input stream (the console).
        Scanner scanner = new Scanner(System.in);

        // Return the next line of input, effectively capturing a string of text entered by the user.
        return scanner.nextLine();
    }
}
