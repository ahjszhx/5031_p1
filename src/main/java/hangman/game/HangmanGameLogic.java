package hangman.game;

import hangman.exception.NonAlphabeticInputException;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static hangman.game.GlobalReference.ERROR_PENALTY_SCORE;
import static hangman.game.GlobalReference.MAX_SCORE;
import static hangman.game.GlobalReference.MAX_TRIES;
import static hangman.game.GlobalReference.wordList;

public class HangmanGameLogic {

    private final String secretWord;
    private final Set<Character> guessedLetters;
    private int wrongGuesses;
    private int score;

    public HangmanGameLogic() {
        secretWord = wordList.get(new Random().nextInt(wordList.size()));
        System.out.println(secretWord);
        guessedLetters = new HashSet<>();
        wrongGuesses = 0;
        score = MAX_SCORE;
    }

    public String getCurrentState() {
        StringBuilder currentState = new StringBuilder();
        return currentState.toString();
    }

    public void makeGuess(char input) throws NonAlphabeticInputException {
    }


    public boolean isGameWon() {
        return false;
    }

    public boolean isGameLost() {
        //System.out.println(wrongGuesses >= MAX_TRIES);
        return wrongGuesses >= MAX_TRIES;
    }

    public String getSecretWord() {
        return secretWord;
    }

    public Set<Character> getGuessedLetters() {
        return guessedLetters;
    }

    public int getWrongGuesses() {
        return wrongGuesses;
    }

    public int getScore() {
        return score;
    }
}
