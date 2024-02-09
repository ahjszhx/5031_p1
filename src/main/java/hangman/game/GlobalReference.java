package hangman.game;

import hangman.utils.WordParser;

import java.util.List;

/**
 * The GlobalReference class serves as a centralized repository for constants and shared resources used across the hangman game.
 * This class provides a convenient way to access game-related settings and data that are essential for game mechanics,
 * ensuring consistency and ease of maintenance.
 */
public class GlobalReference {

    /**
     * MAX_TRIES represents the maximum number of incorrect guesses allowed for a player in the game.
     * This constant defines the difficulty level of the game, limiting the number of attempts a player has to guess the word correctly.
     */
    public static final int MAX_TRIES = 6;

    /**
     * MAX_SCORE defines the maximum score a player can achieve in a single game.
     * This score can be used to assess the player's performance or to implement scoring-based game features.
     */
    public static final int MAX_SCORE = 120;

    /**
     * ERROR_PENALTY_SCORE specifies the score penalty for each incorrect guess made by the player.
     * This penalty reduces the player's score for each mistake, influencing the game's challenge and strategy.
     */
    public static final int ERROR_PENALTY_SCORE = 20;

    /**
     * wordList is a collection of words used in the game, loaded from an external source through the WordParser class.
     * This list is essential for the game's operation, providing the words that players will attempt to guess.
     */
    public static final List<String> wordList = WordParser.loadWords();
}
