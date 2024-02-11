import hangman.exception.NonAlphabeticInputException;
import hangman.game.HangmanGameLogic;
import hangman.game.HangmanUI;
import hangman.game.input.ScannerInput;
import hangman.game.input.UserInput;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyChar;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the HangmanUI class. These tests verify the behavior of the Hangman game's user interface,
 * including handling incorrect guesses, non-alphabetic input, winning the game, and losing the game scenarios.
 */
public class HangmanUITest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Mock
    private HangmanGameLogic gameLogic;
    @Mock
    private UserInput userInput;

    private HangmanUI ui;


    /**
     * Sets up the testing environment before each test. This includes initializing mocks,
     * redirecting System.out to capture output, and creating an instance of HangmanUI with mocked dependencies.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        System.setOut(new PrintStream(outContent));
        ui = new HangmanUI(gameLogic, userInput);
    }

    /**
     * Restores System.out after each test to avoid affecting other tests.
     */
    @AfterEach
    public void restoreSystemOutStream() {
        System.setOut(originalOut);
    }

    /**
     * Tests that the game ends after three incorrect guesses.
     *
     * This test simulates a scenario where the user makes three guesses ('a', 'z', 'b'),
     * with the assumption that these guesses do not match any letters in the secret word "apple".
     * The game logic is mocked to return specific behaviors:
     * - `isGameLost()` returns false for the first three checks (indicating the game has not yet been lost),
     *   and true on the fourth check (indicating the game is now considered lost).
     * - `isGameWon()` consistently returns false, indicating the game has not been won.
     * - `getSecretWord()` returns "apple" as the secret word for this game session.
     *
     * The `HangmanUI` instance (ui) is then used to start the game play, and the test verifies that
     * the `makeGuess()` method is called exactly once with each of the guessed letters.
     * This ensures that the game logic correctly processes each guess and that the game ends
     * after the predetermined number of incorrect guesses.
     */
    @Test
    public void testGameEndsAfterThreeWrongGuesses() {
        when(userInput.nextLine()).thenReturn("a", "z", "b");
        when(gameLogic.isGameLost()).thenReturn(false, false, false, true);
        when(gameLogic.isGameWon()).thenReturn(false);
        when(gameLogic.getSecretWord()).thenReturn("apple");
        //ui = new HangmanUI(gameLogic, userInput);
        ui.play();

        verify(gameLogic, times(1)).makeGuess('a');
        verify(gameLogic, times(1)).makeGuess('z');
        verify(gameLogic, times(1)).makeGuess('b');
    }

    /**
     * Tests that non-alphabetic input throws a NonAlphabeticInputException and that the UI handles it by displaying an error message.
     */
    @Test
    void testNonAlphabeticInput() {
        when(userInput.nextLine()).thenReturn("1");
        doThrow(new NonAlphabeticInputException()).when(gameLogic).makeGuess(anyChar());
        when(gameLogic.isGameLost()).thenReturn(false, true);
        ui.play();

        String output = outContent.toString();
        assertTrue(output.contains("Input must be a single alphabetic character"), "Expected NonAlphabeticInputException detected output.");
    }

    /**
     * Verifies that entering the correct letters to guess the secret word results in a win, displaying congratulations messages.
     */
    @Test
    void testGameWin() {
        when(userInput.nextLine()).thenReturn("p", "l", "e", "a"); // 根据secretWord假设的字母
        when(gameLogic.isGameWon()).thenReturn(false, false, false, false, true); // 最后一次调用返回true
        when(gameLogic.getSecretWord()).thenReturn("apple");

        ui.play();

        assertTrue(outContent.toString().contains("Congratulations"));
        assertTrue(outContent.toString().contains("apple"));
    }

    /**
     * Tests that making a series of incorrect guesses results in a loss, verifying that the game over message and the correct word are displayed.
     */
    @Test
    void testGameLoss() {
        when(userInput.nextLine()).thenReturn("z", "q", "x", "d", "t", "w"); // 假设的错误字母
        when(gameLogic.isGameLost()).thenReturn(false, false, false, false, false, true); // 最后一次调用返回true
        when(gameLogic.getSecretWord()).thenReturn("apple");

        ui.play();

        assertTrue(outContent.toString().contains("Game over"));
        assertTrue(outContent.toString().contains("apple"));
    }


}
