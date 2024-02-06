package hangman.game;

import hangman.exception.NonAlphabeticInputException;

import java.util.Scanner;

public class HangmanUI {

    private HangmanGameLogic gameLogic;

    private UserInput userInput;

    public HangmanUI(HangmanGameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    public HangmanUI(HangmanGameLogic gameLogic, UserInput userInput) {
        this.gameLogic = gameLogic;
        this.userInput = userInput;
    }


    public void play() {
    }

}
