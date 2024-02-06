package hangman.game;


import hangman.exception.NonAlphabeticInputException;
import hangman.game.input.ScannerInput;
import hangman.game.input.UserInput;

/**
 * Represents the User Interface for the Hangman game, managing interactions between the user and the game logic.
 * This class is responsible for displaying game state to the user, handling user input, and updating the game state accordingly.
 */

public class HangmanUI {

    // The game logic instance that this UI interacts with.
    private HangmanGameLogic gameLogic;

    // The input handler used to read user input.
    private UserInput userInput;

    /**
     * Constructs a HangmanUI instance with the specified game logic and user input mechanism.
     *
     * @param gameLogic The game logic to be used by this UI.
     * @param userInput The input mechanism to be used for reading user input.
     */
    public HangmanUI(HangmanGameLogic gameLogic, UserInput userInput) {
        this.gameLogic = gameLogic;
        this.userInput = userInput;
    }

    /**
     * Constructs a HangmanUI instance with the specified game logic and scanner input mechanism.
     * This constructor is specific for cases where a ScannerInput is provided for user input.
     *
     * @param gameLogic The game logic to be used by this UI.
     * @param scannerInput The scanner input mechanism to be used for reading user input.
     */
    public HangmanUI(HangmanGameLogic gameLogic, ScannerInput scannerInput){
        this.gameLogic = gameLogic;
        this.userInput = scannerInput;
    }


    public void play() {
        while (!gameLogic.isGameWon() && !gameLogic.isGameLost()) {
            System.out.println("\nCurrent state: " + gameLogic.getCurrentState());
            System.out.println("Guessed letters: " + gameLogic.getGuessedLetters());
            HangmanDrawing.displayHangman(gameLogic.getWrongGuesses());
            System.out.print("Guess a letter: ");
            char guess = userInput.nextLine().toLowerCase().charAt(0);
            try {
                gameLogic.makeGuess(guess);
            } catch (NonAlphabeticInputException e) {
                System.out.println(e.getMessage());
            }
        }
        if (gameLogic.isGameWon()) {
            System.out.println("\nCongratulations! You guessed the word: " + gameLogic.getSecretWord());
        } else if (gameLogic.isGameLost()) {
            System.out.println("\nGame over! The word was: " + gameLogic.getSecretWord());
            HangmanDrawing.displayHangman(gameLogic.getWrongGuesses());
        }
        System.out.println("\nYour score is " + gameLogic.getScore());
    }

}
