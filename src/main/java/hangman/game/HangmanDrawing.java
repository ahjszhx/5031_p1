package hangman.game;

/**
 * The HangmanDrawing class is responsible for visually representing the state of the hangman
 * based on the number of incorrect guesses a player has made. This class provides a static method
 * to display different stages of the hangman drawing, enhancing the user experience by visually
 * indicating how close the player is to losing the game.
 */
public class HangmanDrawing {

    /**
     * Displays the current state of the hangman drawing based on the number of wrong guesses.
     * The drawing progresses from an empty gallows to a fully drawn hangman as the player makes
     * incorrect guesses. Each stage of the drawing corresponds to a specific number of wrong guesses,
     * incrementally adding parts to the hangman figure.
     *
     * @param wrongGuesses The number of incorrect guesses made by the player so far.
     */
    public static void displayHangman(int wrongGuesses) {
        switch (wrongGuesses) {
            // Display the head of the hangman.
            case 1 -> {
                System.out.println(" +---++");
                System.out.println(" |   ||");
                System.out.println(" O   ||");
                System.out.println("     ||");
                System.out.println("     ||");
                System.out.println("     ||");
                System.out.println("=======");
            }
            // Display the head and torso.
            case 2 -> {
                System.out.println(" +---++");
                System.out.println(" |   ||");
                System.out.println(" O   ||");
                System.out.println(" |   ||");
                System.out.println("     ||");
                System.out.println("     ||");
                System.out.println("=======");
            }
            // Display the head, torso, and one arm.
            case 3 -> {
                System.out.println(" +---++");
                System.out.println(" |   ||");
                System.out.println(" O   ||");
                System.out.println("/|   ||");
                System.out.println("     ||");
                System.out.println("     ||");
                System.out.println("=======");
            }
            // Display the head, torso, and both arms.
            case 4 -> {
                System.out.println(" +---++");
                System.out.println(" |   ||");
                System.out.println(" O   ||");
                System.out.println("/|\\  ||");
                System.out.println("     ||");
                System.out.println("     ||");
                System.out.println("=======");
            }
            // Display the head, torso, both arms, and one leg.
            case 5 -> {
                System.out.println(" +---++");
                System.out.println(" |   ||");
                System.out.println(" O   ||");
                System.out.println("/|\\  ||");
                System.out.println("/    ||");
                System.out.println("     ||");
                System.out.println("=======");
            }
            // Display the complete hangman figure.
            case 6 -> {
                System.out.println(" +---++");
                System.out.println(" |   ||");
                System.out.println(" O   ||");
                System.out.println("/|\\  ||");
                System.out.println("/ \\  ||");
                System.out.println("     ||");
                System.out.println("=======");
            }
            // Display the gallows with no hangman figure for zero wrong guesses.
            default -> {
                System.out.println(" +---++");
                System.out.println(" |   ||");
                System.out.println("     ||");
                System.out.println("     ||");
                System.out.println("     ||");
                System.out.println("     ||");
                System.out.println("=======");
            }
        }
    }

}
