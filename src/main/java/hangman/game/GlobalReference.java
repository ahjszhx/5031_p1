package hangman.game;

import hangman.utils.WordParser;

import java.util.List;

public class GlobalReference {

    public static final int MAX_TRIES = 6;

    public static final int MAX_SCORE = 120;

    public static final int ERROR_PENALTY_SCORE = 20;

    public static final List<String> wordList = WordParser.loadWords();
}
