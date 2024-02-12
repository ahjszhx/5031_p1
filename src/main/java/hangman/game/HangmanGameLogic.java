package hangman.game;

import hangman.exception.NonAlphabeticInputException;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static hangman.game.GlobalReference.ERROR_PENALTY_SCORE;
import static hangman.game.GlobalReference.MAX_SCORE;
import static hangman.game.GlobalReference.MAX_TRIES;
import static hangman.game.GlobalReference.wordList;

/**
 * Represents the logic behind the Hangman game, handling the game state including guesses,
 * score, and win/loss conditions.
 */
public class HangmanGameLogic {

    private static HangmanGameLogic instance;

    // The word that players try to guess.
    private String secretWord;

    // Letters that have been guessed by the player.
    private final Set<Character> guessedLetters;

    // The number of incorrect guesses made by the player.
    private int wrongGuesses;

    // The player's current score.
    private int score;

    /**
     * Constructs a new HangmanGameLogic instance, selecting a secret word at random
     * from a predefined list and initializing the game state.
     */
    private HangmanGameLogic() {
        secretWord = wordList.get(new Random().nextInt(wordList.size()));
        //System.out.println(secretWord);
        guessedLetters = new HashSet<>();
        wrongGuesses = 0;
        score = MAX_SCORE;
    }

    /**
     * Provides access to the singleton instance of the game logic. If the instance
     * does not exist, it creates one; otherwise, it returns the existing instance.
     *
     * @return The singleton instance of HangmanGameLogic.
     */
    public static HangmanGameLogic getInstance(){
        if(instance==null){
            instance = new HangmanGameLogic();
        }
        return instance;
    }

    /**
     * Resets the game to its initial state with a new secret word and clears
     * all guesses and scores.
     */
    public void resetGame(){
        this.secretWord = wordList.get(new Random().nextInt(wordList.size()));
        guessedLetters.clear();
        wrongGuesses = 0;
        score = MAX_SCORE;
    }

    /**
     * Returns the current state of the secret word, showing guessed letters and hiding
     * unguessed letters with underscores.
     *
     * @return A string representing the current visible state of the secret word.
     */
    public String getCurrentState() {
        StringBuilder currentState = new StringBuilder();
        for (char letter : secretWord.toCharArray()) {
            if (guessedLetters.contains(letter)) {
                currentState.append(letter);
            } else {
                currentState.append('_');
            }
        }
        return currentState.toString();
    }

    /**
     * Processes a player's guess, updating the game state accordingly.
     *
     * @param input The character guessed by the player.
     * @throws NonAlphabeticInputException if the input is not an alphabetic character.
     */
    public void makeGuess(char input) throws NonAlphabeticInputException {
        if (!Character.isAlphabetic(input)) {
            throw new NonAlphabeticInputException();
        }
        if (!isGameLost() && !isGameWon()) {
            if (secretWord.indexOf(input) >= 0 && !guessedLetters.contains(input)) {
                guessedLetters.add(input);
            } else if (!guessedLetters.contains(input)) {
                wrongGuesses++;
                score = Math.max(0, score - ERROR_PENALTY_SCORE);
                guessedLetters.add(input);
            }
        }
    }

    /**
     * Checks if the game has been won.
     *
     * @return true if all letters in the secret word have been guessed, false otherwise.
     */
    public boolean isGameWon() {
        for (char letter : secretWord.toCharArray()) {
            if (!guessedLetters.contains(letter)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the game has been lost.
     *
     * @return true if the number of wrong guesses exceeds the maximum allowed, false otherwise.
     */
    public boolean isGameLost() {
        return wrongGuesses >= MAX_TRIES;
    }

    /**
     * Gets the secret word that players are trying to guess.
     *
     * @return The secret word for the current game.
     */
    public String getSecretWord() {
        return secretWord;
    }

    /**
     * Retrieves the set of characters that have been guessed by the player.
     * This includes both correct and incorrect guesses.
     *
     * @return A set of guessed letters.
     */
    public Set<Character> getGuessedLetters() {
        return guessedLetters;
    }

    /**
     * Gets the number of incorrect guesses made by the player.
     * This value is used to determine if the game is lost.
     *
     * @return The count of wrong guesses.
     */
    public int getWrongGuesses() {
        return wrongGuesses;
    }

    /**
     * Retrieves the player's current score.
     * The score decreases with each incorrect guess and can be used to
     * track the player's performance throughout the game.
     *
     * @return The current score of the player.
     */
    public int getScore() {
        return score;
    }
}
