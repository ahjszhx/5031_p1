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

    /**
     * Orchestrates the game play, looping through game states until the game is won or lost.
     * During each loop, it displays the game state, prompts the user for a guess, and processes that guess.
     */
    public void play() {
        while (!gameLogic.isGameWon() && !gameLogic.isGameLost()) {
            displayGameState();
            char guess = promptUserForGuess();
            processUserGuess(guess);
        }
        displayEndGameMessage();
    }

    /**
     * Displays the current state of the game, including the word being guessed (with blanks for missing letters),
     * guessed letters, and the state of the hangman drawing based on incorrect guesses.
     */
    private void displayGameState() {
        System.out.println("\nCurrent state: " + gameLogic.getCurrentState());
        System.out.println("Guessed letters: " + gameLogic.getGuessedLetters());
        HangmanDrawing.displayHangman(gameLogic.getWrongGuesses());
    }

    /**
     * Prompts the user to guess a letter, ensuring the input is properly captured and returned.
     *
     * @return The character guessed by the user.
     */
    private char promptUserForGuess() {
        System.out.print("Guess a letter: ");
        char input = 0;
        try {
            input = userInput.nextLine().toLowerCase().charAt(0);
        } catch (Exception e) {
            System.out.println("Please input a letter");
        }
        return input;
    }

    /**
     * Processes the user's guess by attempting to update the game state accordingly.
     * If the input is not a valid alphabetical character, an error message is displayed.
     *
     * @param guess The user's guessed letter.
     */
    private void processUserGuess(char guess) {
        try {
            gameLogic.makeGuess(guess);
        } catch (NonAlphabeticInputException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Displays a message at the end of the game, indicating whether the user won or lost, and the correct word.
     * It also prompts the user for a replay decision.
     */
    private void displayEndGameMessage() {
        if (gameLogic.isGameWon()) {
            System.out.println("\nCongratulations! You guessed the word: " + gameLogic.getSecretWord());
        } else if (gameLogic.isGameLost()) {
            System.out.println("\nGame over! The word was: " + gameLogic.getSecretWord());
            HangmanDrawing.displayHangman(gameLogic.getWrongGuesses());
        }
        replay(gameLogic);
    }

    /**
     * Handles the logic for replaying the game, including resetting the game state and prompting the user to play again.
     *
     * @param gameLogic The game logic instance to reset for a new game.
     */
    private void replay(HangmanGameLogic gameLogic) {
        System.out.println("\nYour score is " + gameLogic.getScore());
        gameLogic.resetGame();
        System.out.println("\n Do you want to play again ? y/n");
        if(userInput.nextLine().equals("y")){
            this.play();
        }
    }

}
