package hangman;

import hangman.game.HangmanGameLogic;
import hangman.game.HangmanUI;
import hangman.game.input.ScannerInput;

/**
 * The Main class serves as the entry point for the hangman game application.
 * It initializes the necessary components for the game to run, including user input handling
 * and the user interface, then starts the game.
 */
public class Main {

    /**
     * The main method is the entry point of the application. It sets up the game by creating instances
     * of the ScannerInput for handling console input, the HangmanGameLogic for managing the game's logic,
     * and the HangmanUI for handling the interaction between the user and the game logic.
     *
     * @param args Command-line arguments passed to the application (not used in this application).
     */
    public static void main(String[] args) {

        // Create an instance of ScannerInput to read input from the console.
        ScannerInput scannerInput = new ScannerInput();

        // Initialize the HangmanUI with a new instance of HangmanGameLogic and the scanner input.
        // This setup ties the user interface to the game logic and input mechanism.
        HangmanUI hangmanUI = new HangmanUI(HangmanGameLogic.getInstance(),scannerInput);

        // Start the game. This method call begins the gameplay loop, handling user inputs and game state updates
        // until the game concludes (either win or lose).
        hangmanUI.play();
    }
}

