package Wukong;

import java.util.Arrays;
import java.util.List;

/**
 * @author Tianfa Zhu
 * @author Tashia Tamara
 *
 * The PrimaryCommands class handles and validates the primary commands that a player can use in the game.
 * It also provides a way to list all valid commands.
 */
public class PrimaryCommands {

    // List of valid commands that the player can input
    private List<String> commandsList = Arrays.asList(
            "go", "quit", "collect", "guide", "inventory", "drop", "map", "inspect"
    );

    /**
     * Validates the given command by checking if it is in the list of valid commands.
     *
     * @param command The command to be validated.
     * @return null if the command is valid, or an error message if the command is unknown.
     */
    public String handleCommand(String command) {
        if (commandsList.contains(command)) {
            return null;
        } else {
            return "Unknown command. The correct commands are: " + String.join(" ", commandsList) + ".";
        }
    }
    
    public String listCommands() {
        return String.join(" ", commandsList);
    }
}
