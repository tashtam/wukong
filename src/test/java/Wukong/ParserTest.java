package Wukong;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

/**
 * The ParserTest class is designed to test the functionality of the Parser class,
 * ensuring that it correctly interprets and parses user commands from input.
 * This test class includes various scenarios to validate both valid and invalid commands,
 * as well as the handling of commands without additional parameters.
 * 
 * @author Tianfa Zhu
 */

public class ParserTest {

    @Test
    public void testValidCommand() {
        String input = "go north\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        Parser parser = new Parser(scanner);
        Command command = parser.getCommand();

        // Check that the command is correctly parsed
        assertEquals("go", command.getPrimaryCommand());
        assertEquals("north", command.getAdditionalCommand());
        assertFalse(command.isUnknown());
        assertTrue(command.hasAdditionalCommand());
    }

    @Test
    public void testInvalidCommand() {
        String input = "run away\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        Parser parser = new Parser(scanner);
        Command command = parser.getCommand();

        assertTrue(command.isUnknown());
        assertTrue(command.hasAdditionalCommand());
        assertEquals("away", command.getAdditionalCommand());
    }

    @Test
    public void testCommandWithoutSecondWord() {
        String input = "guide\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        Parser parser = new Parser(scanner);
        Command command = parser.getCommand();

        assertEquals("guide", command.getPrimaryCommand());
        assertNull(command.getAdditionalCommand());
        assertFalse(command.isUnknown());
        assertFalse(command.hasAdditionalCommand());
    }

    @Test
    public void testToStringWithAdditionalCommand() {
        Command command = new Command("go", "north");
        assertEquals("go north", command.toString());
    }

    @Test
    public void testToStringWithoutAdditionalCommand() {
        Command command = new Command("guide", null);
        assertEquals("guide", command.toString());
    }
}
