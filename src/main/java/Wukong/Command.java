package Wukong;

/**
 * @author Tianfa Zhu
 *
 * The Command class represents a user command with a primary command and an optional additional command.
 */
public class Command {

    private String primaryCommand;
    private String additionalCommand;

    /**
     * Constructs a Command with a primary command and an optional additional command.
     *
     * @param primaryCommand    The main command to execute.
     * @param additionalCommand An optional additional command or argument.
     */
    public Command(String primaryCommand, String additionalCommand) {
        this.primaryCommand = primaryCommand;
        this.additionalCommand = additionalCommand;
    }

    public String getPrimaryCommand() {
        return primaryCommand;
    }

    public String getAdditionalCommand() {
        return additionalCommand;
    }

    public boolean isUnknown() {
        return primaryCommand == null;
    }

    public boolean hasAdditionalCommand() {
        return additionalCommand != null;
    }

    @Override
    public String toString() {
        return hasAdditionalCommand() ? primaryCommand + " " + additionalCommand : primaryCommand;
    }
}
