package Wukong;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
