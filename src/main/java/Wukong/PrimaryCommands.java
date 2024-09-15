package Wukong;

import java.util.Arrays;
import java.util.List;

public class PrimaryCommands {

    private List<String> commandsList = Arrays.asList(
            "go", "quit", "collect", "guide", "inventory", "drop", "map"
    );

    public String handleCommand(String command) {
        if (commandsList.contains(command)) {
            return null;
        } else {
            return "Unknown command. The correct commands are: " + String.join(" ", commandsList);
        }
    }

    public String listCommands() {
        return String.join(" ", commandsList);
    }
}
