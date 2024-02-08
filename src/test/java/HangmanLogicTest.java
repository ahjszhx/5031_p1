import hangman.exception.NonAlphabeticInputException;
import hangman.game.HangmanGameLogic;

import hangman.utils.WordParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import java.util.ArrayList;
import java.util.List;
import static hangman.game.GlobalReference.MAX_TRIES;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the logic of the Hangman game, including initial game state, handling of guesses,
 * and detection of win/loss conditions. This class uses the {@link HangmanGameLogic} to
 * simulate different scenarios and validate the correctness of the game's logic.
 */
public class HangmanLogicTest {

    // Instance of the game logic to be tested.
    private HangmanGameLogic gameLogic;

    // Stores characters not in the secret word, used for simulating incorrect guesses.
    List<Character> errorInputAlphabet = new ArrayList<>();

    /**
     * Sets up the testing environment before each test. This includes initializing the game
     * logic and preparing a list of characters that are not in the secret word.
     */
    @BeforeEach
    void setUp() {
        gameLogic = HangmanGameLogic.getInstance();
        gameLogic.resetGame();
        for (char ch = 'a'; ch <= 'z'; ch++) {
            errorInputAlphabet.add(ch);
        }
        for (Character cc : gameLogic.getSecretWord().toCharArray()) {
            errorInputAlphabet.remove(cc);
        }
    }

    /**
     * Tests that the word parser correctly loads a non-empty list of words.
     */
    @Test
    void testWordParser() {
        List<String> words = WordParser.loadWords();
        assertNotNull(words, "Word list should not be null");
        assertFalse(words.isEmpty(), "Word list should not be empty");
    }

    /**
     * Verifies the initial state of the game, ensuring no guesses have been made.
     */
    @Test
    void testInitialGameState() {
        assertEquals(0, gameLogic.getWrongGuesses(), "Initial wrong guesses count should be zero.");
        assertTrue(gameLogic.getGuessedLetters().isEmpty(), "Guessed letters list should be empty at the game start.");
    }

    /**
     * Ensures non-alphabetic inputs are correctly handled by throwing a specific exception.
     */
    @Test
    void testNonAlphabeticInputThrowsException() {
        Executable action1 = () -> gameLogic.makeGuess('1');
        Executable action2 = () -> gameLogic.makeGuess('*');
        assertThrows(NonAlphabeticInputException.class, action1, "Non-alphabetic input should throw NonAlphabeticInputException.");
        assertThrows(NonAlphabeticInputException.class, action2, "Non-alphabetic input should throw NonAlphabeticInputException.");
    }

    /**
     * Tests the game's response to a correct letter guess, including the absence of penalty.
     */
    @Test
    void testCorrectGuess() {
        char correctLetter = gameLogic.getSecretWord().charAt(0);
        gameLogic.makeGuess(correctLetter);
        int initScore = gameLogic.getScore();
        assertTrue(gameLogic.getGuessedLetters().contains(correctLetter), "Guessed letters should contain the correct guess.");
        assertEquals(0, gameLogic.getWrongGuesses(), "Wrong guesses count should remain zero after a correct guess.");
        assertEquals(gameLogic.getScore(), initScore, "Score should equals to initial score after a correct guess.");
    }

    /**
     * Tests the game's response to an incorrect letter guess, including the increment of the wrong guesses count
     * and the reduction of the score.
     */
    @Test
    void testIncorrectGuess() {
        char incorrectLetter = errorInputAlphabet.get(0);
        int initScore = gameLogic.getScore();
        gameLogic.makeGuess(incorrectLetter);
        // Asserts that the incorrect letter is added to the guessed letters list
        assertTrue(gameLogic.getGuessedLetters().contains(incorrectLetter), "Guessed letters should contain the incorrect guess.");
        // Verifies the wrong guesses count is incremented
        assertEquals(1, gameLogic.getWrongGuesses(), "Wrong guesses count should be one after an incorrect guess.");
        // Checks if the score decreases after a wrong guess
        assertTrue(gameLogic.getScore() < initScore, "Score should decrease after a wrong guess.");
    }

    /**
     * Verifies that repeating a guess does not affect the game's state in terms of wrong guesses count and score.
     */
    @Test
    void testRepeatedGuess() {
        char letter = gameLogic.getSecretWord().charAt(0);
        gameLogic.makeGuess(letter);
        int scoreAfterFirstGuess = gameLogic.getScore();
        int wrongGuessesAfterFirstTry = gameLogic.getWrongGuesses();
        gameLogic.makeGuess(letter);
        // Asserts that the wrong guesses count remains unchanged
        assertEquals(wrongGuessesAfterFirstTry, gameLogic.getWrongGuesses(), "Wrong guesses count should not change after repeating a guess.");
        // Verifies that the score does not change after a repeated guess
        assertEquals(scoreAfterFirstGuess, gameLogic.getScore(), "Score should not change on repeated correct guess.");
    }

    /**
     * Tests the condition for winning the game, ensuring all letters in the secret word have been guessed.
     */
    @Test
    void testWinningGame() {
        String secretWord = gameLogic.getSecretWord();
        for (char letter : secretWord.toCharArray()) {
            gameLogic.makeGuess(letter);
        }
        // Asserts the game is won
        assertTrue(gameLogic.isGameWon(), "Game should be won when all letters are guessed");

        // Further actions after winning should not change the game state
        int wrongGuessesAfterWin = gameLogic.getWrongGuesses();
        int finalScore = gameLogic.getScore();
        gameLogic.makeGuess(errorInputAlphabet.get(1));
        assertEquals(wrongGuessesAfterWin, gameLogic.getWrongGuesses(), "Wrong guesses count should not change after the game is won.");
        assertEquals(finalScore, gameLogic.getScore(), "Score should not change after the game is won.");
    }

    /**
     * Tests the condition for losing the game by reaching the maximum number of wrong guesses.
     */
    @Test
    void testLosingGame() {
        while (gameLogic.getWrongGuesses() < MAX_TRIES) {
            for (Character cc : errorInputAlphabet) {
                gameLogic.makeGuess(cc);
            }
        }
        // Asserts the game is lost
        assertTrue(gameLogic.isGameLost(), "Game should be lost when maximum wrong guesses are reached");

        // Further actions after losing should not change the game state
        int wrongGuessesAfterWin = gameLogic.getWrongGuesses();
        int scoreAfterLoss = gameLogic.getScore();
        gameLogic.makeGuess(errorInputAlphabet.get(1));
        assertEquals(wrongGuessesAfterWin, gameLogic.getWrongGuesses(), "Wrong guesses count should not change after the game is won.");
        assertEquals(scoreAfterLoss, gameLogic.getScore(), "Score should not change after the game is lost.");
    }

}
