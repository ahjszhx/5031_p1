package hangman;

import hangman.game.HangmanGameLogic;
import hangman.game.HangmanUI;
import hangman.game.input.ScannerInput;


public class Main {

    public static void main(String[] args) {
        ScannerInput scannerInput = new ScannerInput();
        HangmanUI hangmanUI = new HangmanUI(new HangmanGameLogic(),scannerInput);
        hangmanUI.play();
    }
}

