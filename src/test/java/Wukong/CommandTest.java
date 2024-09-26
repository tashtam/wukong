package Wukong;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for the {@link Command} class.
 * These tests validate the functionality of command parsing and management,
 * including retrieving the primary and additional commands, checking if a command is unknown,
 * and testing the string representation of the command.
 *
 * @author Yang Cao
 */

public class CommandTest {

    private Command commandWithAdditional;
    private Command commandWithoutAdditional;
    private Command unknownCommand;

    @Before
    public void setUp() {
        // Initialize command objects for testing
        commandWithAdditional = new Command("go", "north");
        commandWithoutAdditional = new Command("look", null);
        unknownCommand = new Command(null, null);
    }

    @Test
    public void testGetPrimaryCommand() {
        // Test primary command retrieval
        assertEquals("go", commandWithAdditional.getPrimaryCommand());
        assertEquals("look", commandWithoutAdditional.getPrimaryCommand());
        assertNull(unknownCommand.getPrimaryCommand());
    }

    @Test
    public void testGetAdditionalCommand() {
        // Test additional command retrieval
        assertEquals("north", commandWithAdditional.getAdditionalCommand());
        assertNull(commandWithoutAdditional.getAdditionalCommand());
        assertNull(unknownCommand.getAdditionalCommand());
    }

    @Test
    public void testIsUnknown() {
        // Test if the command is unknown (primaryCommand == null)
        assertFalse(commandWithAdditional.isUnknown());
        assertFalse(commandWithoutAdditional.isUnknown());
        assertTrue(unknownCommand.isUnknown());
    }

    @Test
    public void testHasAdditionalCommand() {
        // Test if the command has an additional command
        assertTrue(commandWithAdditional.hasAdditionalCommand());
        assertFalse(commandWithoutAdditional.hasAdditionalCommand());
        assertFalse(unknownCommand.hasAdditionalCommand());
    }

    @Test
    public void testToString() {
        // Test string representation of the command
        assertEquals("go north", commandWithAdditional.toString());
        assertEquals("look", commandWithoutAdditional.toString());
        assertNull(unknownCommand.toString());
    }
}
