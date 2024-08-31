package Wukong;

import java.util.Scanner;

public class Parser {

    private Scanner keyBoard;
    private PrimaryCommands validPrimaryCommands;

    public Parser(Scanner keyBoard) {
        this.keyBoard = keyBoard;
        this.validPrimaryCommands = new PrimaryCommands();
    }

    public Command getCommand() {
        System.out.print("> ");

        String[] tokens = keyBoard.nextLine().trim().split(" ", 2);
        String word1 = tokens.length > 0 ? tokens[0] : null;
        String word2 = tokens.length > 1 ? tokens[1] : null;

        String validationMessage = validPrimaryCommands.handleCommand(word1);
        if (validationMessage == null) {
            return new Command(word1, word2);
        } else {
            System.out.println(validationMessage);
            return new Command(null, word2); // Or handle as needed
        }
    }

    public String showCommands() {
        return validPrimaryCommands.listCommands();
    }
}
