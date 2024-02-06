package hangman.game;

public class HangmanDrawing {

    public static void displayHangman(int wrongGuesses) {
        switch (wrongGuesses) {
            case 1 -> {
                System.out.println(" +---++");
                System.out.println(" |   ||");
                System.out.println(" O   ||");
                System.out.println("     ||");
                System.out.println("     ||");
                System.out.println("     ||");
                System.out.println("=======");
            }
            case 2 -> {
                System.out.println(" +---++");
                System.out.println(" |   ||");
                System.out.println(" O   ||");
                System.out.println(" |   ||");
                System.out.println("     ||");
                System.out.println("     ||");
                System.out.println("=======");
            }
            case 3 -> {
                System.out.println(" +---++");
                System.out.println(" |   ||");
                System.out.println(" O   ||");
                System.out.println("/|   ||");
                System.out.println("     ||");
                System.out.println("     ||");
                System.out.println("=======");
            }
            case 4 -> {
                System.out.println(" +---++");
                System.out.println(" |   ||");
                System.out.println(" O   ||");
                System.out.println("/|\\  ||");
                System.out.println("     ||");
                System.out.println("     ||");
                System.out.println("=======");
            }
            case 5 -> {
                System.out.println(" +---++");
                System.out.println(" |   ||");
                System.out.println(" O   ||");
                System.out.println("/|\\  ||");
                System.out.println("/    ||");
                System.out.println("     ||");
                System.out.println("=======");
            }
            case 6 -> {
                System.out.println(" +---++");
                System.out.println(" |   ||");
                System.out.println(" O   ||");
                System.out.println("/|\\  ||");
                System.out.println("/ \\  ||");
                System.out.println("     ||");
                System.out.println("=======");
            }
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
