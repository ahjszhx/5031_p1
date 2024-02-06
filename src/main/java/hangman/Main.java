package hangman;

import hangman.game.HangmanGameLogic;
import hangman.game.HangmanUI;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        HangmanUI hangmanUI = new HangmanUI(new HangmanGameLogic());
        hangmanUI.play();
    }
}

