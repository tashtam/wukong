package Wukong;

import java.util.Scanner;

/**
 * @author Tianfa Zhu
 *
 * The Parser class is responsible for reading user input, parsing commands, and validating them.
 * It reads input from the keyboard and splits it into primary and secondary commands.
 */
public class Parser {

    private Scanner keyBoard;
    private PrimaryCommands validPrimaryCommands;

    /**
     * Constructs a Parser that reads from the specified Scanner object.
     *
     * @param keyBoard The Scanner object used to read user input.
     */
    public Parser(Scanner keyBoard) {
        this.keyBoard = keyBoard;
        this.validPrimaryCommands = new PrimaryCommands();
    }

    /**
     * Reads and parses the next user command from the input.
     * Validates the primary command using PrimaryCommands and returns a Command object.
     *
     * @return A Command object containing the parsed primary and secondary commands.
     *         If the primary command is invalid, a message is printed, and a Command with a null primary command is returned.
     */
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

    /**
     * Returns a list of valid commands.
     *
     * @return A string listing all valid primary commands.
     */
    public String showCommands() {
        return validPrimaryCommands.listCommands();
    }
}
