package Wukong;

public class Command {

    private String primaryCommand;
    private String additionalCommand;

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
