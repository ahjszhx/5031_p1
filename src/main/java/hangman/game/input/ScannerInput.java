package hangman.game.input;

import java.util.Scanner;

public class ScannerInput implements UserInput{
    @Override
    public String nextLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
