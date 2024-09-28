package Wukong;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * The PrimaryCommandsTest class is designed to test the functionality of the PrimaryCommands 
 * class, which handles various primary commands in the game. This test class verifies the 
 * correct processing of valid commands, handling of invalid commands, and the listing of 
 * available commands.
 * 
 * @author Tianfa Zhu
 */

public class PrimaryCommandsTest {

    private PrimaryCommands primaryCommands;

    @Before
    public void setUp() {
        primaryCommands = new PrimaryCommands();
    }

    @Test
    public void testValidCommand() {
        assertNull(primaryCommands.handleCommand("go"));
        assertNull(primaryCommands.handleCommand("quit"));
        assertNull(primaryCommands.handleCommand("collect"));
        assertNull(primaryCommands.handleCommand("guide"));
        assertNull(primaryCommands.handleCommand("inventory"));
        assertNull(primaryCommands.handleCommand("drop"));
        assertNull(primaryCommands.handleCommand("map"));
        assertNull(primaryCommands.handleCommand("inspect"));
    }

    @Test
    public void testInvalidCommand() {
        String result = primaryCommands.handleCommand("invalidCommand");
        assertEquals("Unknown command. The correct commands are: go quit collect guide inventory drop map inspect.", result);
    }

    @Test
    public void testListCommands() {
        String expectedCommands = "go quit collect guide inventory drop map inspect";
        assertEquals(expectedCommands, primaryCommands.listCommands());
    }
}
